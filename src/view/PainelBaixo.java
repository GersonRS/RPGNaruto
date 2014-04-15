package view;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modelo.vo.Item;

@SuppressWarnings("serial")
public class PainelBaixo extends JPanel {

	private List<Item> inventario;
	private ArrayList<Item> inventatioTemp;
	private ArrayList<Inventario> invent;
	private ArrayList<Inventario> equip;
	private ArrayList<Poder> power;
	private ArrayList<Poder> slotPwer;
	private JLabel fundo, arrastar;
	private boolean pressionouAcao;

	public PainelBaixo() {

		setPreferredSize(new Dimension(150, 450));
		setLayout(null);
		setVisible(true);

		fundo = new JLabel(new ImageIcon(getClass().getClassLoader()
				.getResource("tudoBaixo.png")));
		fundo.setBounds(0, 0, 150, 450);
		add(fundo);
		arrastar = new JLabel();
		fundo.add(arrastar);

		invent = new ArrayList<Inventario>();
		equip = new ArrayList<Inventario>();
		power = new ArrayList<Poder>();
		slotPwer = new ArrayList<Poder>();
		inventatioTemp = new ArrayList<Item>();
		addLabel(8, power, 5, 330);
		addLabel(4, slotPwer, 5, 418);
		addIventario();
		addEquip();
		power.get(0).setIcon(
				new ImageIcon(getClass().getClassLoader().getResource(
						"Skill fogo.png")));
		power.get(1).setIcon(
				new ImageIcon(getClass().getClassLoader().getResource(
						"shurikenIcon.png")));
		power.get(2).setIcon(
				new ImageIcon(getClass().getClassLoader().getResource(
						"relogio.png")));
	}

	public void addItem() {
		for (int i = 0; i < inventario.size(); i++) {
			invent.get(i).setIcon(inventario.get(i).getImg());
			invent.get(i).setStr(inventario.get(i).getStr());
			invent.get(i).setAgi(inventario.get(i).getAgi());
			invent.get(i).setDef(inventario.get(i).getDef());
			invent.get(i).setJut(inventario.get(i).getJut());
			invent.get(i).setHp(inventario.get(i).getHp());
			invent.get(i).setCh(inventario.get(i).getCh());
		}
	}

	public void addEquip() {
		int inc = 32;
		int j = 0;
		int incY = 0;
		for (int i = 0; i < 9; i++) {
			if (i > 0 && i % 3 == 0) {
				incY += inc;
				j = 0;
			}
			equip.add(new Inventario());
			equip.get(i).setBounds(26 + inc * j, 30 + incY, 32, 32);
			fundo.add(equip.get(i));
			j++;
		}
	}

	public void addIventario() {
		int inc = 36;
		int j = 0;
		int incY = 0;
		for (int i = 0; i < 20; i++) {
			if (i > 0 && i % 4 == 0) {
				incY += inc;
				j = 0;
			}
			invent.add(new Inventario());
			invent.get(i).setBounds(5 + inc * j, 132 + incY, 32, 32);
			fundo.add(invent.get(i));
			j++;
		}
	}

	public void addLabel(int num, ArrayList<Poder> array, int x, int y) {
		int inc = 36;
		int j = 0;
		int incY = 0;
		for (int i = 0; i < num; i++) {
			if (i > 0 && i % 4 == 0) {
				incY += inc;
				j = 0;
			}
			array.add(new Poder());
			array.get(i).setBounds(x + inc * j, y + incY, 32, 32);
			fundo.add(array.get(i));
			j++;
		}
	}

	public List<Item> getInventario() {
		return inventario;
	}

	public void setInventario(List<Item> inventario) {
		this.inventario = inventario;
	}

	public ArrayList<Poder> getSlotPwer() {
		return slotPwer;
	}

	@SuppressWarnings("rawtypes")
	public int verificarCliqueParaArrastar(MouseEvent e, ArrayList a) {
		for (int i = 0; i < a.size(); i++)
			if (e.getX() > ((JLabel) a.get(i)).getX()
					&& e.getX() < ((JLabel) a.get(i)).getX() + 32
					&& e.getY() > ((JLabel) a.get(i)).getY()
					&& e.getY() < ((JLabel) a.get(i)).getY() + 32) {
				arrastar.setIcon(((JLabel) a.get(i)).getIcon());
				arrastar.setBounds(e.getX() - 16, e.getY() - 16, 32, 32);
				pressionouAcao = true;
				if (a == invent && inventario.size() != 0) {
					inventatioTemp.add(inventario.get(i));
					inventario.remove(i);
				}
				return i;
			}
		return -1;
	}

	public void verificarSoltar(MouseEvent e, ArrayList<Inventario> a) {
		for (int i = 0; i < a.size(); i++)
			if (pressionouAcao && e.getX() > a.get(i).getX()
					&& e.getX() < a.get(i).getX() + 32
					&& e.getY() > a.get(i).getY()
					&& e.getY() < a.get(i).getY() + 32) {
				a.get(i).setIcon(arrastar.getIcon());
			}

	}

	public void verificarSoltarInvent(MouseEvent e, ArrayList<Inventario> a) {
		for (int i = 0; i < a.size(); i++)
			if (pressionouAcao && e.getX() > a.get(i).getX()
					&& e.getX() < a.get(i).getX() + 32
					&& e.getY() > a.get(i).getY()
					&& e.getY() < a.get(i).getY() + 32) {
				a.get(i).setIcon(arrastar.getIcon());
				if (inventatioTemp.size() != 0) {
					inventario.add(i, inventatioTemp.get(0));
					inventatioTemp.remove(0);
				}
			}

	}

	public void verificarSoltarPoder(MouseEvent e, ArrayList<Poder> a) {
		for (int i = 0; i < a.size(); i++)
			if (pressionouAcao && e.getX() > a.get(i).getX()
					&& e.getX() < a.get(i).getX() + 32
					&& e.getY() > a.get(i).getY()
					&& e.getY() < a.get(i).getY() + 32) {
				a.get(i).setIcon(arrastar.getIcon());
			}

	}

	public ArrayList<Item> getInventatioTemp() {
		return inventatioTemp;
	}

	public void setInventatioTemp(ArrayList<Item> inventatioTemp) {
		this.inventatioTemp = inventatioTemp;
	}

	public ArrayList<Inventario> getInvent() {
		return invent;
	}

	public void setInvent(ArrayList<Inventario> invent) {
		this.invent = invent;
	}

	public ArrayList<Inventario> getEquip() {
		return equip;
	}

	public void setEquip(ArrayList<Inventario> equip) {
		this.equip = equip;
	}

	public ArrayList<Poder> getPower() {
		return power;
	}

	public void setPower(ArrayList<Poder> power) {
		this.power = power;
	}

	public JLabel getFundo() {
		return fundo;
	}

	public void setFundo(JLabel fundo) {
		this.fundo = fundo;
	}

	public JLabel getArrastar() {
		return arrastar;
	}

	public void setArrastar(JLabel arrastar) {
		this.arrastar = arrastar;
	}

	public boolean isPressionouAcao() {
		return pressionouAcao;
	}

	public void setPressionouAcao(boolean pressionouAcao) {
		this.pressionouAcao = pressionouAcao;
	}

	public void setSlotPwer(ArrayList<Poder> slotPwer) {
		this.slotPwer = slotPwer;
	}

}
