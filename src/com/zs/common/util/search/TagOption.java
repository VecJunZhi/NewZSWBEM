package com.zs.common.util.search;

public class TagOption {
	
	private String tagtype;
	private TextEntity text;
	private RadioEntity radio;
	private CheckBoxEntity checkbox;
	private SelectEntity select;
	private ButtonEntity button;
	private TextAreaEntity textarea;
	private DateEntity date;

	public TextEntity getText() {
		return text;
	}

	public void setText(TextEntity text) {
		this.text = text;
	}

	public String getTagtype() {
		return tagtype;
	}

	public void setTagtype(String tagtype) {
		this.tagtype = tagtype;
	}
	
	public RadioEntity getRadio() {
		return radio;
	}

	public void setRadio(RadioEntity radio) {
		this.radio = radio;
	}
	
	
	public CheckBoxEntity getCheckbox() {
		return checkbox;
	}

	public void setCheckbox(CheckBoxEntity checkbox) {
		this.checkbox = checkbox;
	}
	
	public SelectEntity getSelect() {
		return select;
	}

	public void setSelect(SelectEntity select) {
		this.select = select;
	}

	public ButtonEntity getButton() {
		return button;
	}

	public void setButton(ButtonEntity button) {
		this.button = button;
	}
	
	public TextAreaEntity getTextarea() {
		return textarea;
	}

	public void setTextarea(TextAreaEntity textarea) {
		this.textarea = textarea;
	}
	
	public DateEntity getDate() {
		return date;
	}

	public void setDate(DateEntity date) {
		this.date = date;
	}

	public TagOption() {
		text = new TextEntity();
		radio = new RadioEntity();
		checkbox = new CheckBoxEntity();
		select = new SelectEntity();
		button = new ButtonEntity();
		textarea = new TextAreaEntity();
		date = new DateEntity();
	}
	
}
