package com.example.liuj.myrouter.complie;

import com.example.liuj.myrouter.annotation.MyRouter;
import com.google.auto.service.AutoService;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

/**
 * Created by liuj on 2017/12/6.
 * 调试方法：gradle2.3.3 http://www.jianshu.com/p/80a14bc35000
 * gradle3.0.0 不知道用啥方法 !!!
 */
@AutoService(Processor.class)//该标记表明可以处理注解的能力
public class MyRouterProcessor extends AbstractProcessor {

    private Messager mMessager; //日志相关的辅助类

    private void print(String msg) {
        if (null == mMessager) {
            return;
        }
        mMessager.printMessage(Diagnostic.Kind.NOTE, String.format("***** MyRouterProcessor %s ", msg));
    }


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
        mMessager = processingEnvironment.getMessager();

        String msg = "hahahaha";
        mMessager.printMessage(Diagnostic.Kind.NOTE, "***** MyRouterProcessor " + msg);
        mMessager.printMessage(Diagnostic.Kind.NOTE, String.format("***** MyRouterProcessor %s ", msg));
        mMessager.printMessage(Diagnostic.Kind.ERROR, String.format("***** MyRouterProcessor %s ", msg));
        mMessager.printMessage(Diagnostic.Kind.MANDATORY_WARNING, String.format("***** MyRouterProcessor %s ", msg));
        mMessager.printMessage(Diagnostic.Kind.WARNING, String.format("***** MyRouterProcessor %s ", msg));
        mMessager.printMessage(Diagnostic.Kind.OTHER, String.format("***** MyRouterProcessor %s ", msg));


        print("init");
    }

    @Override
    protected synchronized boolean isInitialized() {
        print("isInitialized");

        return super.isInitialized();
    }

    /**
     * 包含本处理器想要处理的注解类型的合法全称。换句话说，你在这里定义你的注解处理器注册到哪些注解上。
     *
     * @return
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        types.add(MyRouter.class.getCanonicalName());
        print("getSupportedAnnotationTypes");

        return types;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        print("getSupportedSourceVersion");

        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        print("process");

        return true;
    }


}