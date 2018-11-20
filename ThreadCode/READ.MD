https://github.com/byhieg/JavaTutorial

# ThreadPoolExecutor.AbortPolicy
- 对拒绝任务抛弃处理，并且抛出异常。 
- 抛出java.util.concurrent.RejectedExecutionException异常
# ThreadPoolExecutor.CallerRunsPolicy
- 这个策略重试添加当前的任务，他会自动重复调用 execute() 方法，直到成功。
- 用于被拒绝任务的处理程序，它直接在 execute 方法的调用线程中运行被拒绝的任务；如果执行程序已关闭，则会丢弃该任务
# ThreadPoolExecutor.DiscardOldestPolicy
- 如果此时线程池中的数量大于corePoolSize，缓冲队列workQueue满，并且线程池中的数量小于maximumPoolSize，建新的线程来处理被添加的任务。
# ThreadPoolExecutor.DiscardPolicy
- 对拒绝任务直接无声抛弃，没有异常信息


# [参考](https://blog.csdn.net/psiitoy/article/details/38587293)
 一个任务通过 execute(Runnable) 方法被添加到线程池，任务就是一个 Runnable 类型的对象，任务的执行方法就是 Runnable 类型对象的 run() 方法。

当一个任务通过 execute(Runnable) 方法欲添加到线程池时，线程池采用的策略如下：

1. 如果此时线程池中的数量小于 corePoolSize ，即使线程池中的线程都处于空闲状态，也要创建新的线程来处理被添加的任务。

2. 如果此时线程池中的数量等于 corePoolSize ，但是缓冲队列 workQueue 未满，那么任务被放入缓冲队列。

3. 如果此时线程池中的数量大于 corePoolSize ，缓冲队列 workQueue 满，并且线程池中的数量小于maximumPoolSize ，建新的线程来处理被添加的任务。

4. 如果此时线程池中的数量大于 corePoolSize ，缓冲队列 workQueue 满，并且线程池中的数量等于maximumPoolSize ，那么通过 handler 所指定的策略来处理此任务。

处理任务的优先级为：

核心线程 corePoolSize 、任务队列 workQueue 、最大线程 maximumPoolSize ，如果三者都满了，使用 handler处理被拒绝的任务。当线程池中的线程数量大于 corePoolSize 时，如果某线程空闲时间超过 keepAliveTime ，线程将被终止。这样，线程池可以动态的调整池中的线程数。

理解了上面关于 ThreadPoolExecutord 的介绍，应该就基本能了解它的一些使用，不过在 ThreadPoolExocutor里面有个关键的 Worker 类，所有的线程都要经过 Worker 的包装。这样才能够做到线程可以复用而无需重新创建线程。

同时 Executors 类里面有 newFixedThreadPool(),newCachedThreadPool() 等几个方法，实际上也是间接调用了ThreadPoolExocutor ，不过是传的不同的构造参数。