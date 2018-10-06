package com.qworldr.mmorpg.provider;

import jdk.internal.org.objectweb.asm.Opcodes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.asm.ClassWriter;
import org.springframework.asm.MethodVisitor;

public class ProviderProxyFactory extends ClassLoader {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProviderProxyFactory.class);
    private static volatile ProviderProxyFactory providerProxyFactory;

    private ProviderProxyFactory() {
    }

    public static ProviderProxyFactory getInstance() {
        if (providerProxyFactory == null) {
            synchronized (ProviderProxyFactory.class) {
                if (providerProxyFactory == null) {
                    providerProxyFactory = new ProviderProxyFactory();
                }
            }
        }
        return providerProxyFactory;
    }

    /**
     * 创建实现泛型的代理类，是superType的子类
     * @param superType
     * @param entity
     * @param key
     * @return
     */
    public EntityProvider createEntityProviderProxy(String superType, Class entity, Class key) {
        Class<?> aClass = defineGenericClass(superType, entity, key);
        Object o = null;
        try {
            o = aClass.newInstance();
        } catch (Exception e) {
            LOGGER.error(String.format("%s<%s,%s>实例化失败", superType, entity.getName(), key.getName()), e);
        }
        return (EntityProvider) o;
    }


    /**
     * 生成继承superType的子类，设置泛型类型为<genericType1,genericType2>
     *
     * @param superType
     * @param genericType1
     * @param genericType2
     * @return
     */
    public Class<?> defineGenericClass(String superType, Class genericType1, Class genericType2) {
        String s = superType.replaceAll("\\.", "/");
        //代理配名
        String className = s + genericType1.getSimpleName() + genericType2.getSimpleName() + "Proxy";
        ClassWriter classWriter = new ClassWriter(0);
        //定义类头
        classWriter.visit(Opcodes.V1_8, 33,
                className,
                String.format("L%s<L%s;L%s;>;", s, genericType1.getName().replaceAll("\\.", "/"), genericType2.getName().replaceAll("\\.", "/")),
                s, null);
        //定义构造方法
        MethodVisitor mv = classWriter.visitMethod(1, "<init>", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, s, "<init>", "()V", false);
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(1, 1);
        mv.visitEnd();
        byte[] bytes = classWriter.toByteArray();
        return defineClass(className.replaceAll("/", "\\."), bytes, 0, bytes.length);
    }
}
