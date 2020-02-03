package frame.panel.main.right;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

public class Dino extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final Dino INSTANCE = new Dino();public static Dino getDino() {return INSTANCE;}
	public static final Logger LOGGER = Logger.getLogger(Dino.class);
	private Integer id = null;

	public Dino() {
		super();
		add(frame.panel.main.right.dino.General.getGeneral());
		add(frame.panel.main.right.dino.Stats.getHealth());
		add(frame.panel.main.right.dino.Stats.getStamina());
		add(frame.panel.main.right.dino.Stats.getOxygen());
		add(frame.panel.main.right.dino.Stats.getFood());
		add(frame.panel.main.right.dino.Stats.getWeight());
		add(frame.panel.main.right.dino.Stats.getMeleeDamage());

		JButton add = new JButton("Add");
		JButton edit= new JButton("Edit");
		add.setPreferredSize(new Dimension(120,25));
		edit.setPreferredSize(new Dimension(120,25));
		add.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				insertD();
				frame.panel.main.center.Dino.getDino().eanble();
			}});
		edit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				updateD(id);
				frame.panel.main.center.Dino.getDino().eanble();
			}});
		add(add);
		add(edit);

		setPreferredSize(new Dimension(255,575));
		setBackground(Color.CYAN);
	}

	public static void update(Integer id)
	{
		getDino().setId(id);
		if(id !=null)
		{
			try (ResultSet rs = DriverManager.getConnection(init.Main.CONN).createStatement().executeQuery(database.select.Dino.selectOne(id));){

				rs.next();
				frame.panel.main.right.dino.General.getGeneral().update(rs.getString(2), rs.getInt(5), getNull(rs.getBoolean(4),rs.wasNull()), rs.getBoolean(3), rs.getInt(26), rs.getInt(25), rs.getInt(27), rs.getInt(28), rs.getByte(24));

				try(ResultSet rsSp = DriverManager.getConnection(init.Main.CONN).createStatement().executeQuery(database.select.Spicies.selectAllForId(rs.getInt(26)));){

					rsSp.next();
					frame.panel.main.right.dino.Stats.getHealth().update(rsSp.getString(3), rs.getLong(6), rs.getByte(7), getNull(rs.getBoolean(8),rs.wasNull()));//
					frame.panel.main.right.dino.Stats.getStamina().update(rsSp.getString(4), rs.getLong(9), rs.getByte(10), getNull(rs.getBoolean(11),rs.wasNull()));
					frame.panel.main.right.dino.Stats.getOxygen().update(rsSp.getString(5), rs.getLong(12), rs.getByte(13), getNull(rs.getBoolean(14),rs.wasNull()));
					frame.panel.main.right.dino.Stats.getFood().update(rsSp.getString(6), rs.getLong(15), rs.getByte(16), getNull(rs.getBoolean(17),rs.wasNull()));
					frame.panel.main.right.dino.Stats.getWeight().update(rsSp.getString(7), rs.getLong(18), rs.getByte(19), getNull(rs.getBoolean(20),rs.wasNull()));
					frame.panel.main.right.dino.Stats.getMeleeDamage().update(rsSp.getString(8), rs.getLong(21), rs.getByte(22), getNull(rs.getBoolean(23),rs.wasNull()));
				} catch (SQLException e) {
					LOGGER.error(e);
				}
			} catch (SQLException e) {
				LOGGER.error(e);
			}
		}
	}
	public static void updateF(Integer id)
	{
		if(id !=null)
		{
			try (ResultSet rs = DriverManager.getConnection(init.Main.CONN).createStatement().executeQuery(database.select.Dino.selectOne(id));)
			{
				rs.next();
				frame.panel.main.right.dino.Stats.getHealth().updateF(rs.getLong(6),rs.getByte(7));
				frame.panel.main.right.dino.Stats.getStamina().updateF(rs.getLong(9),rs.getByte(10));
				frame.panel.main.right.dino.Stats.getOxygen().updateF(rs.getLong(12),rs.getByte(13));
				frame.panel.main.right.dino.Stats.getFood().updateF(rs.getLong(15),rs.getByte(16));
				frame.panel.main.right.dino.Stats.getWeight().updateF(rs.getLong(18),rs.getByte(19));
				frame.panel.main.right.dino.Stats.getMeleeDamage().updateF(rs.getLong(21),rs.getByte(22));
			} catch (SQLException e) {
				LOGGER.error(e);
			}
		}
	}
	public static void updateM(Integer id)
	{
		if(id !=null)
		{
			try (ResultSet rs = DriverManager.getConnection(init.Main.CONN).createStatement().executeQuery(database.select.Dino.selectOne(id));)
			{
				rs.next();
				frame.panel.main.right.dino.Stats.getHealth().updateM(rs.getLong(6),rs.getByte(7));
				frame.panel.main.right.dino.Stats.getStamina().updateM(rs.getLong(9),rs.getByte(10));
				frame.panel.main.right.dino.Stats.getOxygen().updateM(rs.getLong(12),rs.getByte(13));
				frame.panel.main.right.dino.Stats.getFood().updateM(rs.getLong(15),rs.getByte(16));
				frame.panel.main.right.dino.Stats.getWeight().updateM(rs.getLong(18),rs.getByte(19));
				frame.panel.main.right.dino.Stats.getMeleeDamage().updateM(rs.getLong(21),rs.getByte(22));
			} catch (SQLException e) {
				LOGGER.error(e);
			}
		}
	}

	private void insertD() 
	{
		if(frame.panel.main.right.Dino.getDino().isVisible())
		{
			try (Statement st = DriverManager.getConnection(init.Main.CONN).createStatement();)
			{
				database.insert.Dino.insertDino(frame.panel.main.right.dino.General.getGeneral().getInsertPart(),
						frame.panel.main.right.dino.Stats.getHealth().getInsertPart(),
						frame.panel.main.right.dino.Stats.getStamina().getInsertPart(),
						frame.panel.main.right.dino.Stats.getOxygen().getInsertPart(),
						frame.panel.main.right.dino.Stats.getFood().getInsertPart(),
						frame.panel.main.right.dino.Stats.getWeight().getInsertPart(),
						frame.panel.main.right.dino.Stats.getMeleeDamage().getInsertPart());
			} catch (WrongDataException e1) {
				WrongDataException.errorFrame(e1.getMessage());
				LOGGER.error(e1);
			} catch (SQLException e) {
				LOGGER.error(e);
			}
			frame.panel.main.Center.getCenter().updateCenter();
		}
	}
	private void updateD(Integer id) {
		if(id==null){WrongDataException.errorFrame("No dino selected.");LOGGER.error("No dino selected.");return;}
		if(frame.panel.main.right.Dino.getDino().isVisible())
		{
			StringBuilder sb = new StringBuilder("UPDATE dino SET ");
			try {
				sb.append(frame.panel.main.right.dino.General.getGeneral().getUpdatePart());
				sb.append(",");
				sb.append(frame.panel.main.right.dino.Stats.getHealth().getUpdatePart("Health"));
				sb.append(",");
				sb.append(frame.panel.main.right.dino.Stats.getStamina().getUpdatePart("Stamina"));
				sb.append(",");
				sb.append(frame.panel.main.right.dino.Stats.getOxygen().getUpdatePart("Oxygen"));
				sb.append(",");
				sb.append(frame.panel.main.right.dino.Stats.getFood().getUpdatePart("Food"));
				sb.append(",");
				sb.append(frame.panel.main.right.dino.Stats.getWeight().getUpdatePart("Weight"));
				sb.append(",");
				sb.append(frame.panel.main.right.dino.Stats.getMeleeDamage().getUpdatePart("MeleeDamage"));
				sb.append(" Where id = ");
				sb.append(id);
			} catch (WrongDataException e1) {
				WrongDataException.errorFrame(e1.getMessage());
				LOGGER.error(e1);
			}
			LOGGER.info(sb.toString());
			try (Statement st = DriverManager.getConnection(init.Main.CONN).createStatement();)
			{
				st.execute(sb.toString());
			} catch (SQLException e) {
				LOGGER.error(e);
			}
			frame.panel.main.Center.getCenter().updateCenter();

		}


	}

	private static Boolean getNull(boolean value,boolean isNull)
	{
		if(isNull)
		{return null;}
		else
		{return value;}
	}

	private void setId(Integer id) {
		this.id = id;
	}
	public void setStatNames(Integer id)
	{
		try(ResultSet rsSp = DriverManager.getConnection(init.Main.CONN).createStatement().executeQuery(database.select.Spicies.selectAllForId(id));){

			rsSp.next();
			frame.panel.main.right.dino.Stats.getHealth().update(rsSp.getString(3));
			frame.panel.main.right.dino.Stats.getStamina().update(rsSp.getString(4));
			frame.panel.main.right.dino.Stats.getOxygen().update(rsSp.getString(5));
			frame.panel.main.right.dino.Stats.getFood().update(rsSp.getString(6));
			frame.panel.main.right.dino.Stats.getWeight().update(rsSp.getString(7));
			frame.panel.main.right.dino.Stats.getMeleeDamage().update(rsSp.getString(8));
		} catch (SQLException e) {
			LOGGER.error(e);
		}
	}
}
