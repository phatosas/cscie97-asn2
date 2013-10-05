package cscie97.asn2.ecommerce.product;

/**
 * Marker enum representing all current {@link cscie97.asn2.ecommerce.product.Content} types that the Mobile
 * Application Store supports.  Currently only APPLICATION, RINGTONE, and WALLPAPER are supported, but in the future
 * more may be added (see
 * <a href="http://isites.harvard.edu/icb/icb.do?keyword=k97402&panel=icb.pagecontent1356729%3Arforum%3Fforum_id%3D139804&pageid=icb.page626866&pageContentId=icb.pagecontent1356729&view=message&viewParam_message_id=910984#a_icb_pagecontent1356729">
 * this discussion formum post</a> for more detail).
 *
 * @author David Killeffer <rayden7@gmail.com>
 * @version 1.0
 * @see ProductAPI
 * @see Content
 * @see ContentSearch
 * @see Application
 * @see Ringtone
 * @see Wallpaper
 */
public enum ContentType {

    /**
     * Represents an {@link cscie97.asn2.ecommerce.product.Application} content type
     */
    APPLICATION ("Application", "Applications are useful on phones"),

    /**
     * Represents an {@link cscie97.asn2.ecommerce.product.Ringtone} content type
     */
    RINGTONE  ("Ringtone", "Play different sounds when different people call you"),

    /**
     * Represents an {@link cscie97.asn2.ecommerce.product.Wallpaper} content type
     */
    WALLPAPER  ("Wallpaper", "Customize the look of your phone with a wallpaper");

    /**
     * A description of the particular ContentType
     */
    private String description;

    /**
     * A friendly short display string for the ContentType is.
     */
    private String display;

    /**
     * Returns the description for the current ContentType item.
     *
     * @return  the description of the content type
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the short display string for the current ContentType item.
     *
     * @return  the short display value of the content type
     */
    public String display() {
        return this.display;
    }

    /**
     * Creates a new ContentType.
     *
     * @param display      a short, one-word display value for what the current ContentType enum is
     * @param description  a longer descriptive string explaining what the ContentType item is
     */
    ContentType(String display, String description) {
        this.display = display;
        this.description = description;
    }

}
