package kotlin

import kotlin.jvm.internal.unsafe.*
import kotlin.jvm.internal.Intrinsic
import kotlin.reflect.KClass

/**
 * This annotation indicates what exceptions should be declared by a function when compiled to a JVM method.
 *
 * Example:
 *
 * ```
 * throws(IOException::class)
 * fun readFile(name: String): String {...}
 * ```
 *
 * will be translated to
 *
 * ```
 * String readFile(String name) throws IOException {...}
 * ```
 *
 * @property exceptionClasses the list of checked exception classes that may be thrown by the function.
 */
@Retention(AnnotationRetention.SOURCE)
public annotation class Throws(public vararg val exceptionClasses: KClass<out Throwable>)

/**
 * Returns the runtime Java class of this object.
 */
@Intrinsic("kotlin.javaClass.property")
public val <T: Any> T.javaClass : Class<T>
    get() = (this as java.lang.Object).getClass() as Class<T>

/**
 * Returns the Java class for the specified type.
 */
@Intrinsic("kotlin.javaClass.function")
@deprecated("Use the class reference and .java extension property instead: MyClass::class.java", ReplaceWith("T::class.java"))
public fun <reified T: Any> javaClass(): Class<T> = T::class.java

/**
 * Executes the given function [block] while holding the monitor of the given object [lock].
 */
public inline fun <R> synchronized(lock: Any, block: () -> R): R {
    monitorEnter(lock)
    try {
        return block()
    }
    finally {
        monitorExit(lock)
    }
}

/**
 * Returns the annotation type of this annotation.
 */
public fun <T : Annotation> T.annotationType() : Class<out T> =
    (this as java.lang.annotation.Annotation).annotationType() as Class<out T>
