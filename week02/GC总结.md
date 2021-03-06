# GC总结
### 并行GC 和 串行GC
|配置参数|Young GC次数|Young GC时间|Young GC平均时间|Full GC次数|Full GC时间|Full GC平均时间|分配速率|晋升速率|
|--|--|--|--|--|--|--|--|--|
|串行GC ms512m mx512m|9|300ms|33.3ms|7|320ms|45.7ms|2.47gb/sec|405.75gb/sec|
|串行GC ms1g mx1g|6|530ms|88.3ms|0|0ms|0ms|2.61gb/sec|698.1mb/sec|
|并行GC ms512m mx512m|23|340ms|14.8ms|9|310ms|34.4ms|2.22gb/sec|533.26mb/sec|
|并行GC ms1g mx1g|11|500ms|45.5ms|0|0ms|0ms|2.66gb/sec|686.33mb/sec|

### G1 GC
|配置参数|Young GC 次数/平均时间|Mixed GCc|init mark次数/平均时间|concurrent mark次数/平均时间|remark次数/平均时间|Full GC次数/平均时间|分配速率|晋升速率|
|--|--|--|--|--|--|--|--|--|
|ms512m mx512m|32/3.75ms|27/6.67ms|26/1.15ms|51/0.779ms|25/2ms|1/40ms|2.82gb/sec|302.42mb/sec|
|ms1g mx1g|13/31.5ms|0/0ms|1/10ms|2/3.19ms|1/0ms|0/0ms|1.93gb/sec|118.32mb/sec|

### CMS GC
|配置参数|Young GC 次数/平均时间|initial mark次数/平均时间|concurrent mark次数/平均时间|preclean次数/平均时间|abortable preclean次数/平均时间|remark次数/平均时间|concurrent-sweep次数/平均时间|Full GC次数/平均时间|分配速率|晋升速率|
|--|--|--|--|--|--|--|--|--|--|--|
|ms512m mx512m|15/31.3ms|7/0ms|7/10ms|7/0ms|7/30ms|4/0ms|4/0ms|4/46.7ms|1.91gb/sec|494.01.mb/sec|
|ms1g mx1g|7/70ms|0/0ms|0/0ms|0/0ms|0/0ms|0/0ms|0/0ms|0/0ms|2.48gb/sec|713.06.mb/sec|

1. SerialGC是串行，所以更适合单处理器，ParallelGC是并行，所以比较适合多核
2. 随着堆内存分配的增加，SerialGC比ParallelGC在垃圾回收时需要更多的时间，所以多核且内存较大时选择ParallelGC更合适
3. 从测试数据上看相同分配速率下，ParallelGC比SerialGC触发GC更频繁，晋升速率看起来也没差多少，可能是测试数据太少的原因
4. 在停顿时间上，G1 GC和CMS GC更短，对于现在服务器并发较高，要求服务端有较快响应，选择这两个更合适