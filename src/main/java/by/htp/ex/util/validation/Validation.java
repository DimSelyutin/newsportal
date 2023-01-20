package by.htp.ex.util.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



import by.htp.ex.bean.User;
import by.htp.ex.util.validation.validexpression.ExpressionEnum;



public class Validation{

    public List<String> uncorrectFieldName;
    
    public List<String> getUncorrectFieldName() {
        return uncorrectFieldName;
    }

    private Validation(ValidationBuilder validationBuilder) throws ValidException{
        // uncorrectFieldName = validationBuilder.uncorrectFieldName;
        if (validationBuilder.uncorrectFieldName.isEmpty()) {
            
        }
    }
    
    public static class ValidationBuilder implements ObjBuilder<Validation>{
        public final List<String> uncorrectFieldName = new ArrayList<>();
        private User newUser;
        public ValidationBuilder(User _newUser){
            newUser = _newUser;
        }
        
        public ValidationBuilder validLogin(){
            if (!valid(newUser.getLogin(), ExpressionEnum.LOGIN_EXPRESSION.getRegexStr())) {
                uncorrectFieldName.add("Uncorrect Login");    
            }
            return this;
        }

        public ValidationBuilder validEmail(){
            if (!valid(newUser.getEmail(), ExpressionEnum.EMAIL_EXPRESSION.getRegexStr())) {
                uncorrectFieldName.add("Uncorrect Email");    
            }
            return this;
        }

        public ValidationBuilder validPhone(){
            if (!valid(newUser.getPhone(), ExpressionEnum.PHONE_EXPRESSION.getRegexStr())) {
                uncorrectFieldName.add("Uncorrect Phone");    
            }
            
            return this;
        }

        public ValidationBuilder validPassword(){
            if (!valid(newUser.getPassword(), ExpressionEnum.PASSWORD_EXPRESSION.getRegexStr())) {
                uncorrectFieldName.add("Uncorrect Password");    
            }
            return this;
       
        }

        private boolean valid(String str, String s) {
            if (s != null) {
                Pattern val_pattern = Pattern.compile(s); // enum
                Matcher match = val_pattern.matcher(str);
                return match.find();
            }
            return false;
        }

        
        @Override
        public Validation build() throws ValidException {
            return new Validation(this);
        }
    }
}
