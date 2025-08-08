package Pages;

import Utilities.Utility;
import org.openqa.selenium.Cookie;

import java.util.Set;

public class P04AddCandidateApI {

    public String addCandidate(String requestURL,String configReqType,String jsonBody){
        return Utility.InjectRequestUsingPostAPI
                (requestURL,configReqType,jsonBody);
    }

    public String getStatusCode(String requestURL,String configReqType,String jsonBody){
        return Utility.extractStatusCode(addCandidate(requestURL,configReqType,jsonBody));
    }
}
