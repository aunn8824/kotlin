// ImplicitArrayWithFlexibleParameterTypesKt
// WITH_STDLIB
// FILE: ImplicitArrayWithFlexibleParameterTypes.kt

fun getArrayOfFlexibleInts() /* : Array<Int!> */ = arrayOf(JavaClass.getJavaInt())


// FILE: JavaClass.java

public class JavaClass {
    public static Integer getJavaInt() {
        return 10;
    }
}

// FIR_COMPARISON