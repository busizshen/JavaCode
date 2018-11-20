
函数编程的最直接的表现，莫过于将函数作为数据自由传递，
结合泛型推导能力，使代码表达能力获得飞一般的提升。那么，
Java8是怎么支持函数编程的呢？主要有三个核心概念：
```
函数接口(Function)
流(Stream)
聚合器(Collector)
```
https://www.cnblogs.com/snowInPluto/p/5981400.html

https://github.com/CarpenterLee/JavaLambdaInternals


https://blog.csdn.net/qq_24489717/article/details/53837493
# 查看字节码
setting -tools-external tools 添加 bytecode
```
$JDKPath$\bin\javap.exe
-c $FileClass$
$OutputPath$
```