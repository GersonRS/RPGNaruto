package controle;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import view.PainelBaixo;

public class TratarMousePainelBaixo extends MouseAdapter{
	
	PainelBaixo pb;
	
	public TratarMousePainelBaixo(PainelBaixo painel) {
		pb = painel;
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);
		if (e.getButton() == 1) {
			pb.verificarSoltar(e, pb.getEquip());
			pb.verificarSoltarInvent(e, pb.getInvent());
			pb.verificarSoltarPoder(e, pb.getSlotPwer());
			pb.setPressionouAcao(false);
		}
		pb.getArrastar().setIcon(null);
		System.gc();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		super.mouseDragged(e);
		if (e.getX() > 8 && e.getX() < pb.getWidth() - 7)
			pb.getArrastar().setBounds(e.getX() - 16, pb.getArrastar().getY(), 32, 32);
		if (e.getY() < pb.getHeight() - 10 && e.getY() > 8)
			pb.getArrastar().setBounds(pb.getArrastar().getX(), e.getY() - 16, 32, 32);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		if (e.getButton() == 1) {

			pb.verificarCliqueParaArrastar(e, pb.getPower());

			int k = pb.verificarCliqueParaArrastar(e, pb.getInvent());
			if (k >= 0) {
				pb.getInvent().get(k).setIcon(null);
			}

			k = pb.verificarCliqueParaArrastar(e, pb.getEquip());
			if (k >= 0) {
				pb.getEquip().get(k).setIcon(null);

			}
			k = pb.verificarCliqueParaArrastar(e, pb.getSlotPwer());
			if (k >= 0) {
				pb.getSlotPwer().get(k).setIcon(null);

			}
		}
	}
	
}
