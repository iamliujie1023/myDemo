package com.example.liuj.myrouter.complie;

import com.example.liuj.myrouter.annotation.MyRouter;
import com.example.liuj.utils.fileutil.MyFileRead;
import com.example.liuj.utils.fileutil.MyFileWrite;
import com.google.auto.service.AutoService;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

/**
 * Created by liuj on 2017/12/6.
 * 调试方法：gradle2.3.3 http://www.jianshu.com/p/80a14bc35000
 * gradle3.0.0 不知道用啥方法 !!!
 */
@AutoService(Processor.class)//该标记表明可以处理注解的能力
public class MyRouterProcessor extends AbstractProcessor {

    private synchronized void write(String status) {
//        String raw = MyFileRead.getStringFromFile("fileProcessor", "log");
//        if (raw == null) {
//            raw = "AbstractProcessor : MyRouterProcessor";
//        }
//        String msg = String.format("%s \n --> %s", raw, status);
//
//        MyFileWrite.writeStringToFile(msg, "fileProcessor", "log");
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        LogUtil.println(" init");
        write("init");
    }

    @Override
    protected synchronized boolean isInitialized() {
        LogUtil.println(" isInitialized");
        write("isInitialized");

        return super.isInitialized();
    }

    /**
     * 包含本处理器想要处理的注解类型的合法全称。换句话说，你在这里定义你的注解处理器注册到哪些注解上。
     * @return
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        LogUtil.println(" getSupportedAnnotationTypes");
        LogUtil.println(" getSupportedAnnotationTypes --> MyRouter.class.getCanonicalName = " + MyRouter.class.getCanonicalName());

        write("getSupportedAnnotationTypes --> MyRouter.class.getCanonicalName = " + MyRouter.class.getCanonicalName());

        Set<String> types = new LinkedHashSet<>();
        types.add(MyRouter.class.getCanonicalName());

        return super.getSupportedAnnotationTypes();
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        //     * 支持的JDK最新版本号
        LogUtil.println(" getSupportedSourceVersion");

        write("getSupportedSourceVersion");
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        LogUtil.println(" process");
        write("process");
        return false;
    }


}