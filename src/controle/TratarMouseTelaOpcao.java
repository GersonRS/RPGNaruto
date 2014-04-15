package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Fachada;
import view.Botao;
import view.Inicial;
import view.Opcoes;

public class TratarMouseTelaOpcao implements ActionListener{

	private Opcoes o;

	public TratarMouseTelaOpcao(Opcoes o) {
		this.o = o;
	}

	public void actionPerformed(ActionEvent e) {		
		if (e.getSource() == o.getVoltar()){
			o.dispose();
			new Inicial();
		}
		for (Botao b : o.getBotoes()) {
			if (e.getSource() == b){
				o.dispose();
				Fachada.carregarJogo(b.getId());
			}
		}

	}
}
