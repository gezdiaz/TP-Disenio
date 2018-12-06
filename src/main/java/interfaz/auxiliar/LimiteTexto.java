package interfaz.auxiliar;

import java.awt.Toolkit;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

public class LimiteTexto extends DocumentFilter {

	private int limite;
	
	public LimiteTexto(int limite) {
		super();
		this.limite = limite;
	}

	@Override
	public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
			throws BadLocationException {
		// TODO Auto-generated method stub
		if ((fb.getDocument().getLength() + string.length()) <= limite) {
            super.insertString(fb, offset, string, attr);
		}else {
            Toolkit.getDefaultToolkit().beep();
		}
	}

	@Override
	public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
			throws BadLocationException {
		// TODO Auto-generated method stub
		
		if ((fb.getDocument().getLength() + text.length()) <= limite) {
            super.replace(fb, offset, length, text, attrs);
		}else {
            Toolkit.getDefaultToolkit().beep();
		}
	}

	
	
	
	
	
}
