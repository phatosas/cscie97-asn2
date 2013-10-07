package cscie97.asn2.ecommerce.product;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Devices represent unique mobile phones, tablets, etc. that can be used with the Mobile Application Store.
 * {@link cscie97.asn2.ecommerce.product.Content} items may be used with particular Devices.  Note that the
 * {@link cscie97.asn2.ecommerce.product.IProductAPI} will only contain one instance of each unique Device to
 * conserve resources (following the Flyweight pattern).
 *
 * @author David Killeffer <rayden7@gmail.com>
 * @version 1.0
 * @see ProductAPI
 * @see Content
 */
public class Device {

    /**
     * Unique identifier for this particular device
     */
    private String id;

    /**
     * Name of this device.
     */
    private String name;

    /**
     * Company responsible for building this Device.
     */
    private String manufacturer;

    /**
     * Class constructor.
     *
     * @param id            unique identifier for this particular device
     * @param name          name of this device
     * @param manufacturer  company responsible for building this device
     */
    public Device(String id, String name, String manufacturer) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
    }

    /**
     * Returns the unique identifier for this particular device
     *
     * @return  unique identifier for this device
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the unique identifier for this particular device
     *
     * @param id  identifier for the device
     */
    protected void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the name of the device
     *
     * @return  name of the device
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the device
     *
     * @param name the name to use for the device
     */
    protected void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the manufacturer responsible for building the device
     *
     * @return  manufacturer responsible for building the device
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * Sets the name of the manufacturer responsible for building the device
     *
     * @param manufacturer  name of the manufacturer responsible for building the device
     */
    protected void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * Convenience method for debugging; returns a string representation of all the object's properties.
     *
     * @return  string containing all the object properties
     */
    public String toString() {
        return "Device: [id:"+this.id+", name:"+this.name+", manufacturer:"+this.manufacturer+"]";
    }

    /**
     * Checks that all required fields are set, and that all device item values are valid.
     *
     * @param device  the item to be validated for correct properties
     * @return  true if all properties are valid, false otherwise
     */
    public static boolean validateDevice(Device device) {
        return (
                   (device.getId() != null && device.getId().length() > 0) &&
                   (device.getName() != null && device.getName().length() > 0) &&
                   (device.getManufacturer() != null && device.getManufacturer().length() > 0)
        );
    }

    /**
     * Since {@link cscie97.asn2.ecommerce.product.Device} objects may be added to collections, and also since
     * the {@link cscie97.asn2.ecommerce.product.IProductAPI} enforces that all device items be unique, this method
     * provides a way to determine if another {@link cscie97.asn2.ecommerce.product.Device} item is the same as the
     * current one.  Uses the Apache Commons {@link org.apache.commons.lang3.builder.EqualsBuilder} to determine if
     * the two objects are indeed equal to each other.
     *
     * @param compare  the {@link cscie97.asn2.ecommerce.product.Device} item to compare to the current object to test for equality
     * @return  true if the objects are the same, false otherwise
     * @see <a href="http://stackoverflow.com/questions/27581/overriding-equals-and-hashcode-in-java">http://stackoverflow.com/questions/27581/overriding-equals-and-hashcode-in-java</a>
     * @see <a href="http://www.java-tutorial.ch/core-java-tutorial/equalsbuilder">http://www.java-tutorial.ch/core-java-tutorial/equalsbuilder</a>
     * @see <a href="http://commons.apache.org/proper/commons-lang/javadocs/api-2.6/org/apache/commons/lang/builder/EqualsBuilder.html">http://commons.apache.org/proper/commons-lang/javadocs/api-2.6/org/apache/commons/lang/builder/EqualsBuilder.html</a>
     */
    public boolean equals(Object compare){
        if (compare == null)
            return false;
        if (!(compare instanceof Device))
            return false;
        if (compare == this)
            return true;

        Device rhs = (Device) compare;
        return new EqualsBuilder()
                .appendSuper(super.equals(compare))
                .append(this.id, rhs.getId())
                .append(this.name, rhs.getName())
                .append(this.manufacturer, rhs.getManufacturer())
                .isEquals();
    }

    /**
     * Since {@link cscie97.asn2.ecommerce.product.Device} objects may be added to collections, and also since
     * the {@link cscie97.asn2.ecommerce.product.IProductAPI} enforces that all Device items be unique, this method
     * provides a way to get the unique hash code for the current device.  Uses the Apache Commons
     * {@link org.apache.commons.lang3.builder.HashCodeBuilder} to generate a unique hash code for the current item
     * based on two randomly chosen unique prime numbers and all the object properties.
     *
     * @return  a unique integer hash code for this particular object
     * @see <a href="http://stackoverflow.com/questions/27581/overriding-equals-and-hashcode-in-java">http://stackoverflow.com/questions/27581/overriding-equals-and-hashcode-in-java</a>
     * @see <a href="http://commons.apache.org/proper/commons-lang/javadocs/api-2.6/org/apache/commons/lang/builder/HashCodeBuilder.html">http://commons.apache.org/proper/commons-lang/javadocs/api-2.6/org/apache/commons/lang/builder/HashCodeBuilder.html</a>
     * @see <a href="http://primes.utm.edu/lists/small/1000.txt">http://primes.utm.edu/lists/small/1000.txt</a>
     */
    public int hashCode() {
        return new HashCodeBuilder(557, 1289)
                .append(this.id)
                .append(this.name)
                .append(this.manufacturer)
                .toHashCode();
    }

}
