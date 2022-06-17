package hello.itemservice.web.validation;


import hello.itemservice.web.validation.form.ItemSaveForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/validation/api/items")
public class ValidationItemApiController {


    @PostMapping("/add")
    public Object addItem(@RequestBody @Validated ItemSaveForm form, BindingResult bindingResult) {
        //@ModelAttribute 는 필드 단위로 정교하게 바인딩이 적용 됨, 특정 필드가 적용되지 않아도 나머지 필드는 정상 바인딩 되고, 'Validator'를 사용한 검증도 적용할 수 있다.
        //@RequestBody 는 HttpMessageConverter 단계에서 JSON 데이터를 객체로 변경하지 못하면 이후 단계 자체가 진행하지 않고 예외가 발생한다. 컨트롤러도 호출되지 않고, 'Validator'도 적용할 수 없다.

        log.info("API 컨트롤러 호출");

        if (bindingResult.hasErrors()) {
            log.info("검증 오류 발생 errors={}", bindingResult);
            return bindingResult.getAllErrors(); //list 가 json 형식으로 바뀜 -> json 형식의 화면을 확인 가능
        }

        log.info("성공 로직 실행");
        return form;
    }
}
