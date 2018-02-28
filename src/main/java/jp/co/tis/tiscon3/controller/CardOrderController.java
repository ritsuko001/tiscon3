package jp.co.tis.tiscon3.controller;

import enkan.component.BeansConverter;
import enkan.component.doma2.DomaProvider;
import enkan.data.HttpResponse;
import jp.co.tis.tiscon3.dao.CardOrderDao;
import jp.co.tis.tiscon3.entity.CardOrder;
import jp.co.tis.tiscon3.form.CardOrderForm;
import kotowari.component.TemplateEngine;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.transaction.Transactional;

import static enkan.util.HttpResponseUtils.RedirectStatusCode.SEE_OTHER;
import static kotowari.routing.UrlRewriter.redirect;

/**
 * カード申し込みに関するcontrollerクラス.
 *
 * @author hirano
 */
public class CardOrderController {

    @Inject
    private TemplateEngine templateEngine;

    @Inject
    private DomaProvider daoProvider;

    @Inject
    private BeansConverter beans;

    private CardOrderDao cardOrderDao;

    @PostConstruct
    public void init() {
        cardOrderDao = daoProvider.getDao(CardOrderDao.class);
    }

    /**
     * 本人登録ページを表示します.
     *
     * @return 本人登録ページresponse
     */
    public HttpResponse inputUser( ) { //引数追加

        return templateEngine.render("cardOrder/user", "form", new CardOrderForm()); //表示
    }




    /**
     * お勤め先登録ページを表示します.
     *
     * @return お勤め先登録ページresponse
     */
    public HttpResponse inputJob(CardOrderForm form) {
        // エラーを出したくないので強制的にエラーを消す.
        //form.setErrors(null);
        if (form.hasErrors()) { //if分追加
            return templateEngine.render("cardOrder/user", "form", form);
        }
        if (form.getJob().equals("経営自営")||form.getJob().equals("会社員")||form.getJob().equals("契約派遣")||form.getJob().equals("公務員")||form.getJob().equals("民間団体")||form.getJob().equals("他有職")) { //条件に当てはまるひとだけjobへ
            return templateEngine.render("cardOrder/job", "form", form);//表示させる
        }
        //完了ページへ
        return templateEngine.render("cardOrder/completed", "form", form);
    }

    /**
     * 本人登録ページに戻ります.
     *
     * @return 本人登録ページresponse
     */
    public HttpResponse modifyUser(CardOrderForm form) {
        // エラーを出したくないので強制的にエラーを消す.
        form.setErrors(null);

        return templateEngine.render("cardOrder/user", "form", form);
    }

    /**
     * カード申し込み情報をDatabaseに登録します.
     *
     * @return 完了ページへのリダイレクトresponse
     */
    @Transactional
    public HttpResponse create(CardOrderForm form) {
        if (form.getEmployerAddress().isEmpty()){
            form.getErrors().add("employerAddress", "入力してください");
        }
        if (form.getEmployerName().isEmpty()){
            form.getErrors().add("employerName", "入力してください");
        }
        if (form.getEmployerPhoneNumber().isEmpty()){
            form.getErrors().add("employerPhoneNumber", "入力してください");
        }
        if (form.getEmployerZipCode().isEmpty()){
            form.getErrors().add("employerZipCode", "入力してください");
        }
        if (form.getIndustryType()==null){
            form.getErrors().add("industryType", "選択してください");
        }
        if (form.getPosition().isEmpty()){
            form.getErrors().add("position", "入力してください");
        }
        if (form.getCompanySize()==null){
            form.getErrors().add("companySize", "選択してください");
        }
        if (form.getCapital()==null){
            form.getErrors().add("capital", "選択してください");
        }
        if (form.getDepartment().isEmpty()){
            form.getErrors().add("department", "入力してください");
        }
        if (form.getEmployeeLength().isEmpty()){
            form.getErrors().add("employeeLength", "入力してください");
        }
        if (form.hasErrors()) {
            return templateEngine.render("cardOrder/job", "form", form);
        }
        CardOrder cardOrder = beans.createFrom(form, CardOrder.class);

        cardOrderDao.insert(cardOrder);

        return redirect(getClass(), "completed", SEE_OTHER);
    }

    /**
     * 完了ページを表示します.
     *
     * @return 完了ページresponse
     */
    public HttpResponse completed() {
        return templateEngine.render("cardOrder/completed");
    }

}
