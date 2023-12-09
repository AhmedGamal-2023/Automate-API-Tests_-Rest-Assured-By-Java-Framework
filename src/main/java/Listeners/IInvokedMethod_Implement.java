package Listeners;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestResult;

public class IInvokedMethod_Implement implements IInvokedMethodListener {


    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        IInvokedMethodListener.super.beforeInvocation(method, testResult);
        System.out.println("Before :"+ method.getTestMethod().getMethodName() +">>>>  of Class :" +method.getTestMethod().getRealClass().getName());
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        IInvokedMethodListener.super.afterInvocation(method, testResult);
        System.out.println("after :"+ method.getTestMethod().getMethodName() +">>>>  of Class :" +method.getTestMethod().getRealClass().getName());
    }


}
