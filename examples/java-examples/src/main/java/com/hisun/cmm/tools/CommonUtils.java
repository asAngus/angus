/**
 * 
 */
package com.hisun.cmm.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import com.hisun.cmm.tools.svn.log.model.Log;
import com.hisun.cmm.tools.svn.log.model.Logentry;
import com.hisun.cmm.tools.svn.log.model.Path;

import joptsimple.OptionParser;
import joptsimple.OptionSet;

/**
 * @author weipeng
 *
 */
public class CommonUtils {
    private static final String HELP = "help";
    private static final String AUTH = "auth";
    private static final String OUT_FILE_NAME = "outFileName";
    private static final String SVN_PATH = "svnPath";
    private static final String START_VERSION = "startVersion";
    private static final String END_VERSION = "endVersion";
    private static final String BASE_DIR = "baseDir";
    private static final String LOG_TAG = "logTag";

    public static void main(String[] args) throws FileNotFoundException {
        OptionParser optionParser = new OptionParser();
        optionParser.accepts(HELP, "Print this message").withRequiredArg();
        optionParser.accepts(AUTH, "AUTH").withRequiredArg();
        optionParser.accepts(SVN_PATH, "svnPath").withRequiredArg();
        optionParser.accepts(START_VERSION, "startVersion").withRequiredArg();
        optionParser.accepts(END_VERSION, "endVersion").withRequiredArg();
        optionParser.accepts(BASE_DIR, "baseDir").withRequiredArg();
        optionParser.accepts(OUT_FILE_NAME, "outFileName").withRequiredArg();
        optionParser.accepts(LOG_TAG, "logTag").withRequiredArg();
        OptionSet parse = optionParser.parse(args);
        String helpText = "";
        if (parse.has(HELP)) {
            StringWriter out = new StringWriter();
            try {
                optionParser.printHelpOn(out);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println(helpText);
            return;
        }
        String auth = "";
        if (parse.has(AUTH)) {
            auth = (String) parse.valueOf(AUTH);
        }
        String baseParse = "./";
        if (parse.has(BASE_DIR)) {
            baseParse = (String) parse.valueOf(BASE_DIR);
        }
        File baseDir = new File(baseParse);

        String svnPath = "./";
        if (parse.has(SVN_PATH)) {
            svnPath = (String) parse.valueOf(SVN_PATH);
        } else {
            return;
        }
        String logTag = "";
        if (parse.has(LOG_TAG)) {
            logTag = (String) parse.valueOf(LOG_TAG);
        }
        String startVersion = "";
        if (parse.has(START_VERSION)) {
            startVersion = (String) parse.valueOf(START_VERSION);
        }
        String endVersion = "";
        if (parse.has(END_VERSION)) {
            endVersion = (String) parse.valueOf(END_VERSION);
        }
        String outFileName = System.currentTimeMillis() + ".lst";
        if (parse.has(OUT_FILE_NAME)) {
            outFileName = (String) parse.valueOf(OUT_FILE_NAME);
        }
        File outFile = new File(baseDir, outFileName);
        outFile.delete();
        System.out.println(startVersion + ":" + endVersion + ":" + auth);
        // ??????§ß????·Ú????
        String[] cmdStrArr = {
                baseDir.getAbsolutePath() + File.separator + "execute.sh",
                startVersion, endVersion, auth };
        String execute = execute(cmdStrArr, new File(svnPath));
        try {
            writeFile(auth, outFile, execute, logTag);
        } catch (Exception e) {
            e.printStackTrace(System.out);
            throw new RuntimeException(e);
        }

    }

    private static void writeFile(String auth, File outFile, String execute,
            String logTag) throws FileNotFoundException {
        Log bean = Log.toBean(execute, Log.class);
        List<Logentry> logentrys = bean.getLogentry();
        List<String> outList = new ArrayList<String>();
        List<String> delList = new ArrayList<String>();
        Set<String> bootList = new LinkedHashSet<String>();
        String bootFormat = "i_boot:%s:%s";
        String fileFormat = "file:%s:%s";
        for (Logentry logentry : logentrys) {
            if (!"".equals(auth) && !"none".equals(auth)
                    && !logentry.getAuthor().equals(auth)) {
                continue;
            }
            if (!"".equals(logTag) && !"none".equals(logTag)
                    && !logentry.getMsg().contains(logTag)) {
                continue;
            }
            List<Path> paths = logentry.getPaths();
            for (Path path : paths) {
                boolean isDelete = false;
                String substring = path.getValue();

                File file = new File(substring);
                if ("M".equals(path.getAction())) {
                } else if ("A".equals(path.getAction())) {
                } else if ("D".equals(path.getAction())) {
                    isDelete = true;
                } else {
                    continue;
                }

                String sp = "/wowealth/";
                int indexOf = substring.indexOf(sp);
                if (-1 == indexOf) {
                    continue;
                }
                substring = substring.substring(indexOf + sp.length());
                int startSplit = substring.indexOf("/");
                int endSplit = substring.indexOf("/", startSplit + 1);
                if (endSplit < 0) {
                    continue;
                }
                String name = substring.substring(startSplit + 1, endSplit);
                String modelName = null;
                if (!substring.endsWith(".jar")) {
                    int modelStartSplit = substring.lastIndexOf("/");
                    int modelEndSplit = substring.lastIndexOf("/",
                            modelStartSplit - 2);
                    if (modelEndSplit < 0) {
                        continue;
                    }
                    // ???????
                    if (!file.getName().contains(".") && !isDelete) {
                        continue;
                    }
                    modelName = substring.substring(modelEndSplit + 1,
                            modelStartSplit);
                }
                String instanceName = "PAY";
                if ("MCA".equals(name.toUpperCase(Locale.getDefault()))) {
                    instanceName = "MCA";
                }

                String outStr = String.format(fileFormat, substring,
                        instanceName);
                // ???????????§Ó?????????§Ò??§Õ???
                if (!isDelete) {
                    if (outList.contains(outStr)) {
                        continue;
                    }
                    outList.add(outStr);
                    if (modelName != null && (modelName.startsWith("O")
                            || modelName.startsWith("S"))) {
                        bootList.add(String.format(bootFormat, modelName,
                                instanceName));
                    }
                } else {
                    outList.remove(outStr);

                    if (!file.getName().contains(".")) {
                        for (String tmp : outList) {
                            if (tmp.contains(substring)) {
                                delList.add(tmp);
                            }
                        }
                    }
                }
            }

        }
        PrintStream printStream = new PrintStream(outFile);
        outList.removeAll(delList);
        for (String string : outList) {
            printStream.println(string);
        }
        printStream.println();
        bootList.add(String.format(bootFormat, "S.PARSVR", "MCA"));
        bootList.add(String.format(bootFormat, "S.PARSVR", "PAY"));
        for (String boot : bootList) {
            printStream.println(boot);
        }

        printStream.flush();
        printStream.close();
    }

    public static String execute(final String[] cmdStrArr, File workFile) {
        StringBuffer resBuf = new StringBuffer();
        Runtime rt = Runtime.getRuntime();
        BufferedReader bufr = null;

        try {
            Process p = rt.exec(cmdStrArr, null, workFile);

            // int exitVal = p.waitFor();
            //
            // // ??§ß?????????????
            // if (0 != exitVal) {
            // return executeCMDRepeat(cmdStrArr);
            // }

            bufr = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            String line = null;
            String lineS = System.getProperty("line.separator");
            while ((line = bufr.readLine()) != null) {
                resBuf.append(line).append(lineS);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // catch (InterruptedException e) {
        // e.printStackTrace();
        // }
        finally {
            if (bufr != null) {
                try {
                    bufr.close();
                } catch (Exception e) {
                }
            }
        }

        return resBuf.toString();
    }

    /**
     * ?????????????
     * 
     * @param cmdStrArr
     * @return
     * @throws CacheException
     */
    public static String executeCMDRepeat(final String[] cmdStrArr) {
        StringBuffer resBuf = new StringBuffer();
        Runtime rt = Runtime.getRuntime();
        BufferedReader bufr = null;

        try {
            Process p = rt.exec(cmdStrArr);

            p.waitFor();

            bufr = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            String line = null;
            while ((line = bufr.readLine()) != null) {
                resBuf.append(line).append("\r\n");
            }
        } catch (IOException e) {
        } catch (InterruptedException e) {
        } finally {
            if (bufr != null) {
                try {
                    bufr.close();
                } catch (Exception e) {
                }

            }
        }

        return resBuf.toString();

    }

}
