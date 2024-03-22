package lk.ncs.apiserver.core.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIResponse {
    private Integer status;
    private Object data;
    private Object error;
    private boolean IsSuccessfull;
    private String message;
}
