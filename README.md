# springmvc-template
这是springmvc的模版工程，封装了互联网公司中需要使用到的各种功能，主要功能服下：  
1.登录校验，如果未登录，则直接打回，同时可通过@NoNeedLogin注解标注到Controller类或者方法上则可以省去登录校验到逻辑  
```java
/**
     * 对于不需要登录的接口可以直接在Controller方法上或者Controller类上加上@NoNeedLogin的注解
     * @param request
     * @param result
     * @return
     */
    @RequestMapping(value = "")
    @NoNeedLogin
    public Object login(@Valid LoginRequest request, BindingResult result) {
        return loginService.login(request);
    }
```
2.在Controller方法中直接写 @LoggedIn User user 这个参数，则可以直接使用到User对象，写Controller方法犹如写普通到业务代码一样，无需关注从何处取当前登录用户信息
```java
/**
     * Controller参数中直接使用 @LogginedIn User user
     * @param user
     * @return
     */
    @RequestMapping("")
    public Object home(@LoggedIn User user) {
        return indexService.assemble(user);
    }
```
3.业务参数校验，参数校验直接在对象前加@Valid注解，同时在参数后面加一个BindingResult的参数即可  
```java
/**
     * 参数校验直接在对象前加@Valid注解，同时在参数后面加一个BindingResult的参数即可
     * @param request
     * @param result
     * @return
     */
    @RequestMapping(value = "")
    @NoNeedLogin
    public Object login(@Valid LoginRequest request, BindingResult result) {
        return loginService.login(request);
    }
```
4.日志记录，本系统会记录每次请求中入参，返回值，调用耗时，同时为了方便分布式日志追踪，引入了 slf4j的MDC，可以保证同一次请求中产生的日志拥有相同的traceId  
```java
2018-04-24 15:50:30.827 [http-bio-8080-exec-5] INFO  [TRACE_ID:377ce243-1ecb-4702-a6f0-917622531215] com.zdp.springmvc.template.filter.LoginResolveFilter - 解析登录信息:user={"mchId":"测试id","name":"测试名","userId":"测试userId"}
2018-04-24 15:50:30.870 [http-bio-8080-exec-5] INFO  [TRACE_ID:377ce243-1ecb-4702-a6f0-917622531215] com.zdp.springmvc.template.interceptor.LogInterceptor - receive request,url=http://localhost:8080/login,params = {"userName":["abc"],"password":["aaa"]}
2018-04-24 15:50:30.872 [http-bio-8080-exec-5] INFO  [TRACE_ID:377ce243-1ecb-4702-a6f0-917622531215] com.zdp.springmvc.template.interceptor.LoginCheckInterceptor - controller方法或者类上有NoNeedLogin注解，无需检测登录
2018-04-24 15:50:30.889 [http-bio-8080-exec-5] INFO  [TRACE_ID:377ce243-1ecb-4702-a6f0-917622531215] com.zdp.springmvc.template.interceptor.LogInterceptor - invoke finished,response = {"bstatus":{"code":0,"desc":"成功","msg":""},"data":{"mchId":"1","name":"abc"}}
2018-04-24 15:50:30.889 [http-bio-8080-exec-5] INFO  [TRACE_ID:377ce243-1ecb-4702-a6f0-917622531215] com.zdp.springmvc.template.interceptor.LogInterceptor - invoke cost time:21ms
```
5.返回值拼装，Controller方法中无需关注返回值拼装的问题，开发者只需要关注返回参数的data域名，比如登录返回值样例： 
```java
{"bstatus":{"code":0,"desc":"成功","msg":""},"data":{"mchId":"1","name":"abc"}}  
```
开发者在Controller方法中只需要返回一个User对象即可  
```java
@RequestMapping(value = "")
    @NoNeedLogin
    public Object login(@Valid LoginRequest request, BindingResult result) {
        return loginService.login(request);
    }
    
    public User login(LoginRequest request) {
        User user = new User();
        user.setMchId("1");
        user.setName("abc");
        return user;
    }
```
6.异常处理，开发者只需要在ResultCode中定义对应的业务异常code，在业务代码中直接跑出BusinessException异常即可，对于异常的处理就交给拦截器吧
```java
@RestController
@RequestMapping("/exception")
public class ExceptionDemo {

     /**
     * BusinessException有2个构造方法，如下
     public BusinessException(ResultCode resultCode, String msg) {
        super(msg);
        this.resultCode = resultCode;
        this.msg = msg;
     }

     public BusinessException(ResultCode resultCode) {
        super(resultCode.getDesc());
        this.resultCode = resultCode;
     }
     * @return
     */
    @RequestMapping("/demo")
    public Object demo() {
        throw new BusinessException(ResultCode.SYSTEM_ERROR);
    }
}
```
只要是执行Controller方法时抛出异常，最终拦截器都将会处理，返回值格式如下
```java
{"bstatus":{"code":2,"desc":"系统异常"}}
```
7.配置文件区分环境
在src/main/profiles下会有3个目录,dev/beta/product（大部分情况下是这3种环境，如果需要其他的环境可以修改pom.xml），将每种
环境下配置不同的配置文件放到这个目录下，比如jdbc.properties,dubbo.properties等等，然后在src/main/resources目录下的spring
配置文件或者代码中取引用这些配置项，maven打包时选择maven package -P ${profile名称，如dev}即可