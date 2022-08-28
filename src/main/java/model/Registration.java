package model;
import lombok.Data;

@Data
public class Registration {
    private boolean success;
    private String accessToken;
    private String refreshToken;
    private User user;

    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }

}
