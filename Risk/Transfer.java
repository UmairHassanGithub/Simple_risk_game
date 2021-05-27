package Risk;
 
import javax.swing.JFrame;
 //Opens a diaologe box asking user how many troops to transfer
public class  Transfer {
	public Transfer(){
		
	}
	public int GetTrans()  {
		JFrame frame =new JFrame();
        CustomDialog customDialog;
        customDialog = new CustomDialog(frame, this);
        customDialog.setLocationRelativeTo(frame);
        customDialog.setVisible(true);
		
		return Integer.parseInt(CustomDialog.ReturnNum);
		//returns a number 
	}
 
 
	
}

