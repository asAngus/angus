#!/bin/sh
. ~/.bash_profile

if [ "$2" != "" ]; then
    HEAD_STR="-r $1:$2"
    
else
    HEAD_STR="-r $1:HEAD"
fi
/usr/local/bin/svn log -v ${HEAD_STR} --xml

#/usr/local/bin/svn log -v -r 9000:HEAD --xml
#/usr/local/bin/svn log -v | sed -n '/'${tag}'/,/--$/ p'|grep " /"