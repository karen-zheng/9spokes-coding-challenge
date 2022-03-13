public class AccountingDataModel {
    private String object_category;
    private String connection_id;
    private String user;
    private String object_creation_date;
    private EntryModel[] data;
    private String currency;
    private String object_origin_type;
    private String object_origin_category;
    private String object_type;
    private String object_class;
    private String balance_date;

    public String getObject_category() {
        return object_category;
    }

    public void setObject_category(String object_category) {
        this.object_category = object_category;
    }

    public String getConnection_id() {
        return connection_id;
    }

    public void setConnection_id(String connection_id) {
        this.connection_id = connection_id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getObject_creation_date() {
        return object_creation_date;
    }

    public void setObject_creation_date(String object_creation_date) {
        this.object_creation_date = object_creation_date;
    }

    public EntryModel[] getData() {
        return data;
    }

    public void setData(EntryModel[] data) {
        this.data = data;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getObject_origin_type() {
        return object_origin_type;
    }

    public void setObject_origin_type(String object_origin_type) {
        this.object_origin_type = object_origin_type;
    }

    public String getObject_origin_category() {
        return object_origin_category;
    }

    public void setObject_origin_category(String object_origin_category) {
        this.object_origin_category = object_origin_category;
    }

    public String getObject_type() {
        return object_type;
    }

    public void setObject_type(String object_type) {
        this.object_type = object_type;
    }

    public String getObject_class() {
        return object_class;
    }

    public void setObject_class(String object_class) {
        this.object_class = object_class;
    }

    public String getBalance_date() {
        return balance_date;
    }

    public void setBalance_date(String balance_date) {
        this.balance_date = balance_date;
    }
};
