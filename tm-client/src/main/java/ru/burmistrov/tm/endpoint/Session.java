
package ru.burmistrov.tm.endpoint;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for session complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="session"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://endpoint.tm.burmistrov.ru/}abstractEntity"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="signature" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="timesTamp" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "session", propOrder = {
    "signature",
    "timesTamp"
})
public class Session
    extends AbstractEntity
{

    protected String signature;
    protected long timesTamp;

    /**
     * Gets the value of the signature property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignature() {
        return signature;
    }

    /**
     * Sets the value of the signature property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignature(String value) {
        this.signature = value;
    }

    /**
     * Gets the value of the timesTamp property.
     * 
     */
    public long getTimesTamp() {
        return timesTamp;
    }

    /**
     * Sets the value of the timesTamp property.
     * 
     */
    public void setTimesTamp(long value) {
        this.timesTamp = value;
    }

}
