package jp.co.tis.tiscon3.form;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class CardOrderForm extends FormBase {

    private static final long serialVersionUID = 1L;

    @NotBlank(message ="※入力してください")
    @Size(max = 60)
    private String kanjiName;

    @NotBlank(message ="※入力してください")
    @Size(max = 90)
    @Pattern(regexp = "^[ァ-ヶー 　]*$")
    private String kanaName;

    @NotBlank(message ="※入力してください")
    @Size(max = 120)
    @Pattern(regexp = "^[a-zA-Z 　]*$")
    private String alphabetName;

    @NotBlank(message ="※入力してください")
    @Size(max = 10,message="※半角数字と/を用いて西暦で入力してください")
    @Pattern(regexp = "\\d{4}/\\d{1,2}/\\d{1,2}$",message="※半角数字と/を用いて西暦で入力してください")
    private String dateOfBirth;

    @NotBlank(message ="※入力してください")
    @Size(max = 6)
    private String gender;

    @NotBlank(message ="※入力してください")
    @Size(max = 8)
    @Pattern(regexp = "^[0-9]{3}-[0-9]{4}$")
    private String zipCode;

    @NotBlank(message ="※入力してください")
    @Size(max = 255)
    private String address;

    @NotBlank(message ="※入力してください")
    @Size(max = 13)
    @Pattern(regexp = "^0[0-9]{1,3}-[0-9]{2,4}-[0-9]{4}$",message ="※半角英数と-を用いて入力してください")
    private String homePhoneNumber;

    @Size(max = 13,message ="※入力に誤りがあります")
    @Pattern(regexp = "^((070|080|090)-[0-9]{4}-[0-9]{4})?$",message ="※半角数字と-を用いて入力してください")
    private String mobilePhoneNumber;

    @NotBlank(message ="※入力してください")
    @Size(max = 255,message ="※入力に誤りがあります")
    @Email
    private String emailAddress;

    @NotBlank(message ="※選択してください")
    @Size(max = 20)
    private String spouse;

    @NotBlank(message ="※選択してください")
    @Size(max = 20)
    private String houseClassification;

    @NotBlank(message ="※入力してください")
    @Size(max = 6,message ="※入力に誤りがあります")
    @Pattern(regexp = "[0-9]*",message ="※半角英数で入力してください")
    private String debt;

    @NotBlank(message ="※選択してください")
    @Size(max = 120)
    private String job;

    @NotBlank(message ="※入力してください")
    @Size(max = 6)
    @Pattern(regexp = "[0-9]*",message ="※半角英数で入力してください")
    private String income;

    @Size(max = 255)
    private String employerName;

    @Size(max = 8)
    @Pattern(regexp = "^([0-9]{3}-[0-9]{4})?$")
    private String employerZipCode;

    @Size(max = 255)
    private String employerAddress;

    @Size(max = 13)
    @Pattern(regexp = "^(0[0-9]{1,3}-[0-9]{2,4}-[0-9]{4})?$")
    private String employerPhoneNumber;

    @Size(max = 255)
    private String industryType;

    @Size(max = 6)
    @Pattern(regexp = "[0-9]*")
    private String capital;

    @Size(max = 255)
    @Pattern(regexp = "[0-9]*")
    private String companySize;

    @Size(max = 255)
    private String position;

    @Size(max = 255)
    private String department;

    @Size(max = 6)
    @Pattern(regexp = "[0-9]*")
    private String employeeLength;

}
