import java.io.File;
import java.io.InputStream;
import java.net.URL;
import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class StickerFactory  {
    public void create(InputStream input, String message) throws Exception {
        // ler a imagem
        BufferedImage original = ImageIO.read(input);


        //criar nova imagem em memória
        int width = original.getWidth();
        int height = original.getHeight();
        int newHeight = height + 150;
        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

        // copiar a imagem original pra nova imagem (em memória)
        Graphics2D graphics = newImage.createGraphics();
        graphics.drawImage(original, 0, 0, null);

        //configurar fonte
        Font font = new Font(Font.SERIF, Font.BOLD, 32);
        graphics.setColor(Color.ORANGE);
        graphics.setFont(font);

        //escrever uma frase na nova imagem
        graphics.drawString(message, width/2 - graphics.getFontMetrics(font).stringWidth(message)/2 , (newHeight - 75) + graphics.getFontMetrics(font).getHeight()/2);

        //escrever a nova imagem em um arquivo
        if (!new File("/home/jeanfrusca/Documentos/Coding/Alura_Java/aula02/gerador-de-stickers/src/images").exists()) {
            new File("/home/jeanfrusca/Documentos/Coding/Alura_Java/aula02/gerador-de-stickers/src/images").mkdirs();
        }
        ImageIO.write(newImage, "png", new File("/home/jeanfrusca/Documentos/Coding/Alura_Java/aula02/gerador-de-stickers/src/images/teste.png"));

    }

public static void main(String[] args) throws Exception {
    StickerFactory factory = new StickerFactory();
    URL inputURL = new URL("https://w7.pngwing.com/pngs/727/488/png-transparent-uzumaki-naruto-illustration-naruto-shippuden-ultimate-ninja-storm-2-naruto-uzumaki-naruto-shippuden-naruto-vs-sasuke-naruto-shippuden-manga-cartoons-fictional-character-thumbnail.png");
    factory.create(inputURL.openStream(), "NARUTOO");
}
}
