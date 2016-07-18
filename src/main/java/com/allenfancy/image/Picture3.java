package com.allenfancy.image;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Picture3 {

	public static void convert(String path) {
		try {
			BufferedImage image = ImageIO.read(new File(path));
			ImageIcon imageIcon = new ImageIcon(image);
			BufferedImage bufferedImage = new BufferedImage(imageIcon.getIconWidth(), imageIcon.getIconHeight(),
					BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics2D = bufferedImage.createGraphics();
			graphics2D.drawOval(46, 46, 200, 200);
			graphics2D.drawImage(imageIcon.getImage(), 0, 0, imageIcon.getImageObserver());
			
			String outFile = path.substring(0, path.lastIndexOf("."));
			ImageIO.write(bufferedImage, "png", new File(outFile + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean colorInRange(int color) {
		int red = (color & 0xff0000) >> 16;
		int green = (color & 0x00ff00) >> 8;
		int blue = (color & 0x0000ff);
		if (red >= color_range && green >= color_range && blue >= color_range)
			return true;
		return false;
	}

	public static int color_range = 210;
	public static Pattern pattern = Pattern.compile("[0-9]*");

	public static boolean isNo(String str) {
		return pattern.matcher(str).matches();
	}

	public static BufferedImage resizeImage(BufferedImage bufferedimage, int w, int h) {
		int type = bufferedimage.getColorModel().getTransparency();
		Graphics2D graphics2d = new BufferedImage(w, h, type).createGraphics();
		graphics2d.drawImage(bufferedimage, 0, 0, w, h, 0, 0, bufferedimage.getWidth(), bufferedimage.getHeight(),
				null);
		graphics2d.dispose();
		return bufferedimage;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path = JOptionPane.showInputDialog(null, "请输入图片目录");
		if (path == null || !new File(path).isDirectory()) {
			JOptionPane.showMessageDialog(null, "输入目录有误！");
			return;
		}
		String color = JOptionPane.showInputDialog(null, "请输入色差范围0~255(建议10~50)");
		if (isNo(color)) {
			color_range = 255 - Integer.parseInt(color);
			File file = new File(path);
			String[] files = file.list();
			for (int i = 0; i < files.length; i++) {
				String ext = files[i].substring(files[i].lastIndexOf(".") + 1);
				if (ext.equals("jpg")) {
					convert(path + "//" + files[i]);
				}
			}
			JOptionPane.showMessageDialog(null, "转换完成！");
		} else {
			JOptionPane.showMessageDialog(null, "输入的数字有误！");
		}
	}

}