package ru.job4j.ood.lsp.menu;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MenuPrinterClassTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testPrint() {
        MenuPrinterClass printer = new MenuPrinterClass();
        SimpleMenu menu = new SimpleMenu();
        menu.add(null, "Item 1", () -> {
        });
        menu.add("Item 1", "Subitem 1.1", () -> {
        });
        menu.add("Item 1", "Subitem 1.2", () -> {
        });
        printer.print(menu);
        String expectedOutput = "Item 1\n    Subitem 1.1\n    Subitem 1.2\n";
        String actualOutput = outContent.toString().replace("\r\n", "\n");
        assertThat(actualOutput).isEqualTo(expectedOutput);
    }
}