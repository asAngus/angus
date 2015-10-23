ngFast
=====================================

首先，该项目原本来源于[angularjs-gulp-browserify-boilerplate](https://github.com/jakemmarsh/angularjs-gulp-browserify-boilerplate)。

这只是个克隆，然后在这之上修改了一些，改用coffee。

如果你是coffee党，厌倦了requires，觉得grunt太麻烦，又在开发angular应用，我想这个种子模板应该很适合你。

---

### 如何开始

```
git clone https://github.com/soliury/ngFast
```

```
cd ngFast && sudo npm install
```

### 主要命令：

1. `gulp dev`：运行本地开发模式，自动watch文件然后编译sass，编译coffee，然后bundler。
2. `npm run build`：编译文件，这个命令其实是调用：`NODE_ENV=dev-online gulp build && NODE_ENV=production gulp build`，改命令会再build下生成两个文件夹，production和dev-online。前者是为了用于线上的测试服务器，后者用于线上的投入运营的服务器。至于为什么要采取这么复杂的方式要添加一个NODE_ENV，我稍后再解释。
3. `gulp tdd`: 运行unit测试。
3. `npm run server:dev`：运行本地server，代理build文件夹下得dev-online，这是为了测试是否build生成的文件能否正确运行，而且可以查看gzip过后的大小。
4. `npm run server:production`：同上。代理production文件夹下的文件。

### 有什么特点

1. 首先这是一个gulp+coffee+browerify来开发angular的模板。
2. 采用gulp作为项目构建辅助开发工具，反正我是已经放弃grunt了。因为gulp好用又快。
3. 用browserify来做前端包依赖管理。用node的require方式来写前端，就一个字，爽。第一次编译时速度不快，但是第一次编译后就会缓存到内存，第二次编译时就十分迅速，一秒以内。
4. `gulp serve`来做本地静态代理，然后用browser-sync同步浏览器和代码。每次运行自动打开本地ip地址，手机上登录该地址也便可以访问，开发时，利于手机端调试。
5. 自动获取ip供browserify使用。在本地开发的时，有时前台调用的api是本地的，自然api就得填写本地的api地址，填写`http://localhost:3000`等当然不行，手机上就没法用，所以运行gulp时会自动探测本地电脑的内网ip，这个ip会传给browserify，在编写程序时，调用`process.env.ip`就行。这样就算是换了一个ip也行，反正每次都会自动获取ip。详情请看config。
6. 一个命令build出两个版本，使得线上测试app和要发布到生产环境的app代码一样，这样就不用线上测试时build一次，然后发布到生产环境时再build一次。而且也难以保证线上测试的代码和要发布到生产环境的代码一致。
7. 用`gulp-plumber`打补丁，再也不用怕sass和coffee代码写错时编译出错导致整个gulp程序不能进行了。



