/** 
 * Copyright (C) 2023 Bonitasoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package $package;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.Base64;

/**
 * This contains the constants for generating the HTML file.
 * The Tree view is inspired from https://www.cssscript.com/tree-view-nested-list/ (MIT license)
 */
public class HtmlTreeGenerator {

    /** Prefix for HTML tree */
    private static final String HTML_TREE_PREFIX = String.join(App.LINE_SEPARATOR,
            "<html>",
            "<script>",
            "!function(e,t){\"object\"==typeof exports&&\"undefined\"!=typeof module?module.exports=t():\"function\"==typeof define&&define.amd?define(t):(e=e||self).listree=t()}(this,(function(){\"use strict\";return function(){const e=document.getElementsByClassName(\"listree-submenu-heading\");Array.from(e).forEach((function(e){e.classList.add(\"collapsed\"),e.nextElementSibling.style.display=\"none\",e.addEventListener(\"click\",(function(t){t.preventDefault();const n=t.target.nextElementSibling;\"none\"==n.style.display?(e.classList.remove(\"collapsed\"),e.classList.add(\"expanded\"),n.style.display=\"block\"):(e.classList.remove(\"expanded\"),e.classList.add(\"collapsed\"),n.style.display=\"none\"),t.stopPropagation()}))}))}}));",
            "</script>",
            "<style>",
            ".listree-submenu-heading{cursor:pointer}ul.listree{list-style:none}ul.listree-submenu-items{list-style:none;border-left:1px dashed black;white-space:nowrap;margin-right:4px;padding-left:20px}div.listree-submenu-heading.collapsed:before{content:\"+\";margin-right:4px}div.listree-submenu-heading.expanded:before{content:\"-\";margin-right:4px}.scrollable-menu{height:auto;max-width:800px;overflow-y:hidden}",
            "</style>",
            "   <ul class=\"listree\">",
            "");
    /** Suffix for HTML tree */
    private static final String HTML_TREE_SUFFIX = String.join(App.LINE_SEPARATOR,
            "   </ul>",
            "<script>",
            "listree();",
            "</script>",
            "</html>",
            "");

    /**
     * Outputs the html file prefix before the tree
     * 
     * @param out the output stream
     * @throws IOException writing exception
     */
    public static void prefix(OutputStream out) throws IOException {
        out.write(HTML_TREE_PREFIX.getBytes());
    }

    /**
     * Outputs the html file suffix after the tree
     * 
     * @param out the output stream
     * @throws IOException writing exception
     */
    public static void suffix(OutputStream out) throws IOException {
        out.write(HTML_TREE_SUFFIX.getBytes());
    }

    /**
     * Write a simple tree entry to the HTML file
     * 
     * @param out the output stream
     * @param label the label text (without image)
     * @throws IOException writing exception
     */
    public static void writeTreeEntry(OutputStream out, String label)
            throws IOException {
        // display the tree entry, whithout children nor image
        out.write(String.join(App.LINE_SEPARATOR,
                // start list item
                "<li>",
                // display label only
                "<div>",
                label + "</div>",
                // end list item
                "</li>",
                "").getBytes());
    }

    /**
     * Write a tree entry to the HTML file
     * 
     * @param out the output stream
     * @param image the URL to image
     * @param label the label text
     * @param hasChildren true when the entry will have children
     * @throws IOException writing exception
     */
    public static void writeTreeEntry(OutputStream out, URL image, String label, boolean hasChildren)
            throws IOException {
        /*
         * Get the image content to insert it in html.
         * Note this is not optimized and leads to duplications.
         */
        String extension = image.getFile().substring(image.getFile().lastIndexOf(".") + 1);
        byte[] imageBytes = extractImageContent(image);

        // display the tree entry, depending on whether it has children
        if (hasChildren) {
            out.write(String.join(App.LINE_SEPARATOR,
                    // start list item
                    "<li>",
                    // display icon and label
                    "<div class=\"listree-submenu-heading\">",
                    "<img src='data:image/" + extension + ";base64," + Base64.getEncoder().encodeToString(imageBytes)
                            + "'>",
                    label + "</div>",
                    // open list for children
                    "<ul class=\"listree-submenu-items\">",
                    "").getBytes());
        } else {
            out.write(String.join(App.LINE_SEPARATOR,
                    // start list item
                    "<li>",
                    // display icon and label
                    "<div>",
                    "<img src='data:image/" + extension + ";base64," + Base64.getEncoder().encodeToString(imageBytes)
                            + "'>",
                    label + "</div>",
                    // end list item
                    "</li>",
                    "").getBytes());
        }
    }

    /**
     * End the tree entry once all its children have been displayed
     * 
     * @param out the output stream
     * @throws IOException writing exception
     */
    public static void endTreeEntryChildren(OutputStream out) throws IOException {
        // end children list and list item
        out.write(String.join(App.LINE_SEPARATOR,
                "</ul>",
                "</li>",
                "").getBytes());
    }

    /**
     * Extract the image content as a byte array
     * 
     * @param image the image URL
     * @return image bytes
     * @throws IOException reading exception
     */
    private static byte[] extractImageContent(URL image) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try (BufferedInputStream imageStream = new BufferedInputStream(image.openStream())) {
            int n = 0;
            byte[] buffer = new byte[1024];
            while ((n = imageStream.read(buffer)) >= 0) {
                output.write(buffer, 0, n);
            }
        }
        byte[] imageBytes = output.toByteArray();
        return imageBytes;
    }

}
