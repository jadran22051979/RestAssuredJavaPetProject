package content;

public class PetContent {
    //Authorization
    public static final String API_KEY="api-key";
    public static final String API_KEY_VALUE="testApiKey";
    //Body
    public static final String BODY_ID="id";
    public static final String BODY_CATEGORY_ID ="category.id";
    public static final String BODY_CATEGORY_NAME ="category.name";
    public static final String BODY_NAME ="name";
    public static final String BODY_STATUS ="status";
    public static final String BODY_CHANGED_NAME ="Changed name";

    //Status
    public static final String STATUS_AVAILABLE="available";

    //Status code
    public static final int STATUS_CODE_200= 200;
    public static final int STATUS_CODE_404= 404;
    //Header
    public static final String HEADER_CONTENT_TYPE ="Content-Type";
    public static final String HEADER_JSON ="application/json";
    public static final String HEADER_ALLOW_METHODS ="access-control-allow-methods";
    public static final String HEADER_ALLOW_METHODS_TYPES ="GET, POST, DELETE, PUT";
    //Negative Tests
    public static final Integer petDtoNegativeId = -9;


}
