## 异常

异常是在程序执行期间发生的事情，它会中断程序指令正常的运行。

1、当在方法中发生错误时，该方法创建一个对象，并将其移交给运行时系统。该对象称为“异常对象”，

2、包含有关错误的信息，包括错误信息发生的类型和程序的状态。创建异常对象并将其移交给运行时系统。这个过程称为“抛出异常”。

3、在方法抛出异常后，运行时系统会尝试寻找一些方式来处理它。这个列表被称为“调用堆栈”。

运行时系统搜索包含能够处理异常的代码块的方法所请求的堆栈。这个代码块叫“异常处理器”。
- 1、搜寻首先从发生的地方开发，然后依次调用方法的倒序检索掉调用堆栈。

- 2、当找到一个相应的处理器时，运行时系统就把这个异常传递给这个处理器。

- 3、当异常被选中时被称为“捕获异常”。异常被捕获以后，异常处理器关闭。

### 异常捕获与处理
此示例在`java.io`中定义的输出类，这些类包含在基本I/O中
```java
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by guo on 2018/2/13.
 * Note：this class will not compile yet
 */
public class ListOfNumber {
    private List<Integer> list;
    private static final int SIZE = 20;
    public ListOfNumber() {
        list = new ArrayList<>();
        for(int  i = 0; i < SIZE; i++) {
            list.add(new Integer(i));
        }
    }
    public void writeList() {
        // The FileWriter constructor throws IOException,which mush be caught.
        PrintWriter out = new PrintWriter(new File("OutFile.txt"));
        for (int i = 0; i < SIZE; i++) {
            // The get(int) mothod throws IndexOutOfBoundsException,which mush bu caugth.
            out.println("value at:" + i + "= " + list.get(i));
        }
        out.close();
    }
}
```
构造函数FileWriter初始化文件上传的输出流。如果文件无法打开，构造函数会抛出一个IOException异常，
第二个对ArryList类的get方法的调用，如果此参数的值小于0或者太大。(超过ArrayList当前包含的元素)，它会抛出出IndexOutOfBoundException异常。
如果尝试编译ListOfNumber类，则编译器将打印有关FileWriter构造函数抛出的异常的错误消息。但是，它不会显示有关get抛出的异常消息。
原因是构造函数IOException抛出的异常是一个检查异常。而get方法抛出的IndexOfBoundException异常是未检查的。

知道了那些地方有异常，接下来我们就可以编写异常处理程序来捕获这些异常

### try块

构造异常处理程序的第一步是封装可能在try块中抛出异常的代码。一般来说，try块看起来像下面这样：

```java
try{
    code
}
catch and finally blocks...
```
示例标记code中的段可以包含一个或多个可能抛出的异常。
每行抛出的异常的都可以用一个单独的一个try块，或者是多个异常放在一个try块中。以下示例非常简单，所有放在一个块中

```java
private List<Integer> list;
private static final int SIZE = 10;
public ListOfNumber() {
    list = new ArrayList<>();
    for(int  i = 0; i < SIZE; i++) {
        list.add(new Integer(i));
    }
  }
public void writeList() {
    // The FileWriter constructor throws IOException,which mush be caught.
    PrintWriter out =null;
    try {
      out =  new PrintWriter(new File("OutFile.txt"));
      for (int i = 0; i < SIZE; i++) {
        // The get(int) mothod throws IndexOutOfBoundsException,which mush bu caugth.
        out.println("value at:" + i + "= " + list.get(i));
      }
      out.close();
    }
    catch and finally blocks...
}
```
如果实在try块中发生异常，那么该异常由其相关联的异常处理器程序将会进行处理。
要将处理程序与try块关联，必须在其后放置一个catch块。

### catch
通过在try块之后直接提供一个或多个catch块，可以将异常处理与try关联。在try块的结尾和第一个之间没有代码
```java
try {

}catch (Exception e) {

}catch (Exception e) {

}
```
每个catch是一个异常处理程序，处理由其参数指示的异常类型。

catch块包含了在调用异常处理程序时执行的代码。当处理程序 是调用堆栈中的第一个与Exception类型匹配的一次抛出类型时。
调用运行时系统将调用异常处理程序。如果抛出的对象可以合法的分配给异常 处理程序的参数，则系统默认匹配。

### 在一个异常处理程序中处理多个类型的异常

在JavaSE7和更高的版本中，单个catch块可以处理多种类型的异常。此功能可以减少代码重复，并减少定义过于宽泛的异常。

```java
catch (IOException | SQLException e ) {
    logger.log(ex);
    throw e;
}
```

### finally块

finall块总是在try块退出时执行。这确保即使发生意外的异常抛出也会执行finally快。但finally块的用处不仅仅是异常处理，它允许程序员避免清理代码绕过return、continue 或 break。

运行时系统总是执行finally块内的语句，而不管try发生什么，所以它是执行清理的完美场所。
```java
finally{
  if (out != null) {
    System.out.println("closing PrintWriter");
    out.close();
  }else {
    System.out.println("PrintWriter not open");
  }
}
```

重要: finally块是防止资源泄漏的关键工具。当关闭文件或恢复资源时，将代码放在finally块中，可以确保资源始终恢复。

### try-with-resource 语句

try-with-resource是JDK 7中一个新的异常处理机制，它能够很容易的关闭在try-catch语句块中使用的资源。
所谓的资源(source)是指在程序完成之后，必须关闭的对象。try-with-resource语句确保了每个资源在语句结束后都能及时关闭。
所有实现类java.lang.AutoCloseable接口可以使用作为资源

```java
public class Demo() {
  public static void main(String ... args) {
    try(Resource res = new Resource()) {
      res.doSome();
    }catch (Exception e) {
      e.printStackTrace();
    }
  }
  class Resource  implements AutoCloseable {
      void doSome(){
        System.out.println("do something")
      }
      @Override
      public void close() throws Exception{
        System.out.println("resource is closed")
      }
  }
}
```

### Throwable类及其子类

继承自Throwable类的对象包括 直接后代(直接从Throwable类继承的对象)和间接后代(从Throwable类的子类或孙子类继承的对象)
Throwable有两个直接的后代：Error和Exception。

#### Error类

当java虚拟机中发生动态链接故障或其他硬件故障时，虚拟机会抛出Erroe。简单的程序不能捕获或抛出Erroe。

#### Exception类
一个Exception子类 RuntimeException保留用于指示不正确的API的异常。运行时异常的一个示例是NullPointException，当方法尝试通过空引用访问对象成员时，会发生此异常。


一条底线原则：** 如果客户端可以合理的从期望异常中恢复，那么使其成为一个已检查异常。如果客户端无法从异常中恢复，请将其设置为未检查异常。 **

## 日志API
如果记录catch中所发生的异常，最好不要手动解析堆栈跟踪并将输出发送到System.err(),而是使用java.util.logging包中的日志记录工具将输出发送到文件
```java
try {
  Handler handler = new FileHandler("OutFile.log");
  logger.getLogger("").addHandler(Handler);
}catch (IOException e) {
  Logger logger = logger.getLogger("package.name");
  //获取堆栈跟踪信息。
  StackTraceElement element[] = e.getStackTrace();
  for (int i = 0; n = element.length; i < n; i++) {
    logger.log(level.WARNING,element[i].getMethodname());
  }
}
```






























































































































































-
