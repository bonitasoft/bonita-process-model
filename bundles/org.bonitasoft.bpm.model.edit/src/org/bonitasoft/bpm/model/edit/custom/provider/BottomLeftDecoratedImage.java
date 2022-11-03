/**
 * Copyright (C) 2022 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.bpm.model.edit.custom.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.edit.provider.ComposedImage;

/**
 * A composed image made of a first image, and of a second image to use as bottom-left decorator
 * 
 * @author Vincent Hemery
 */
public class BottomLeftDecoratedImage extends ComposedImage {

    /**
     * Creates an image with a bottom-left decorator
     * 
     * @param images the two images, with decorator as second
     */
    public BottomLeftDecoratedImage(Collection<?> images) {
        super(images);
        // check images
        if (images.size() != 2) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public List<Point> getDrawPoints(Size size) {
        List<Point> result = super.getDrawPoints(size);
        // place second image at bottom
        result.get(1).y = Math.max(0, size.height - imageSizes.get(1).height);
        return result;
    }
}
