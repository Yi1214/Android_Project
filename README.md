# Android_Project
This respository only contains part of my work on Android APP development.
We also analyzed and optimized the memory of this app.

In this project, we use the Profiler tool of Android Studio to analyze the cause of memory jitter in the project. 
As shown in the figure below, we found that in the process of memory jitter, a large number of objects were created, with a total of 37,086.

![image](https://github.com/Yi1214/Android_Project/blob/master/Memory_Optimization/memory_thrashing.png)

Through the analysis of the object, we found the culprit of creating a large number of objects.

The first reason is that we write the creation of objects such as view in the onCreateView method of the fragment subclass.
Because multiple fragments under the same activity frequently switch, the onCreateView method will be executed repeatedly, so a large number of objects will be created repeatedly.

The second reason is that the data query code of the fragment subclass is redundant.
At the beginning, all the data is taken out, and then conditionally filtered through the for loop traversal, which will result in the creation of a large number of objects that do not belong to the fragment.

The third reason is to write the creation of objects such as views in the onCreateViewHolder method of RecyclerViewAdapter and the getView method of GridViewAdapter.

Based on these issues, we carried out related code optimization work.

First, we optimized the relevant code for data query.
Then, the above-mentioned large number of created objects are unified as global variables, 
and the reuse of the objects is realized through conditional judgment inside the loop calling method, avoiding the creation of objects in the loop and repeated calling methods, thereby solving the stuck problem of APP.

The following figure shows the memory usage in the same time period after our optimization. It can be seen that the number of objects created **has dropped from 37,086 to 11,573, a reduction of about 2/3**. Overall, the memory usage is more stable.

![image](https://github.com/Yi1214/Android_Project/blob/master/Memory_Optimization/memory_optimization.png)
