package fr.isika.cda27.teamJADE.utilz;

import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class CustomRadioButton extends HBox {

	private RadioButton trueBtn;
	private RadioButton falseBtn;
	
	CustomRadioButton () {
		this.trueBtn = new RadioButton("oui");
		this.falseBtn = new RadioButton("non");
		
		this.setAlignment(Pos.CENTER);
		this.setPrefWidth(400);
		this.setSpacing(100);
		
		ToggleGroup toggleGroup = new ToggleGroup();
		trueBtn.setToggleGroup(toggleGroup);
		falseBtn.setToggleGroup(toggleGroup);
		
		trueBtn.setFont(Font.font("Krona One", 14));
		falseBtn.setFont(Font.font("Krona One", 14));
//		falseBtn.setSelected(true);
		
		this.getChildren().addAll(trueBtn,falseBtn);
	}
	
	public boolean isTrueBtnSelected() {
        return trueBtn.isSelected();
    }
	
	public boolean isFalseBtnSelected() {
        return falseBtn.isSelected();
    }

	public RadioButton getTrueBtn() {
		return trueBtn;
	}

	public void setTrueBtn(RadioButton trueBtn) {
		this.trueBtn = trueBtn;
	}

	public RadioButton getFalseBtn() {
		return falseBtn;
	}

	public void setFalseBtn(RadioButton falseBtn) {
		this.falseBtn = falseBtn;
	}
	
	
}
