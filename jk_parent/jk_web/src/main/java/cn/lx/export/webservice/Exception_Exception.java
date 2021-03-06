
package cn.lx.export.webservice;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "Exception", targetNamespace = "http://webservice.export.lx.cn/")
public class Exception_Exception
    extends java.lang.Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private cn.lx.export.webservice.Exception faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public Exception_Exception(String message, cn.lx.export.webservice.Exception faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public Exception_Exception(String message, cn.lx.export.webservice.Exception faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: cn.lx.export.webservice.Exception
     */
    public cn.lx.export.webservice.Exception getFaultInfo() {
        return faultInfo;
    }

}
