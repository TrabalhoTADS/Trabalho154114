package br.univel;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class NumberField extends JTextField {
    public NumberField() {
        this( null );
    }
    public NumberField( String text ) {
        super( text );
        setDocument( new PlainDocument() {
	    @Override
    	    public void insertString( int offs, String str, AttributeSet a ) throws BadLocationException {
                //normalmente apenas uma letra é inserida por vez,
                //mas fazendo assim também previne caaso o usuário
                //cole algum texto
                for( int i = 0; i < str.length(); i++ )
                    if( Character.isDigit( str.charAt( i ) ) == false )
                        return;
                super.insertString( offs, str, a );
            }
        } );
    }
}