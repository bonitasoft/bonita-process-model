/**
 * Copyright (C) 2018 Bonitasoft S.A.
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
package org.bonitasoft.bonita2bpmn.transfo;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.gef.ui.figures.SlidableAnchor;

public class CustomAnchor extends SlidableAnchor {

    public CustomAnchor(IFigure targetFigure) {
        super(targetFigure);
    }

    public CustomAnchor(IFigure targetFigure, PrecisionPoint p) {
        super(targetFigure, p);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.gmf.runtime.draw2d.ui.figures.BaseSlidableAnchor#getOrthogonalLocation(org.eclipse.draw2d.geometry.Point)
     */
    @Override
    public Point getOrthogonalLocation(Point orthoReference) {
        PrecisionPoint ownReference = new PrecisionPoint(getReferencePoint());
        PrecisionRectangle bounds = new PrecisionRectangle(
                CustomRectilinearRouter.getAnchorableFigureBounds(getOwner()));
        getOwner().translateToAbsolute(bounds);
        bounds.expand(0.000001, 0.000001);
        PrecisionPoint preciseOrthoReference = new PrecisionPoint(orthoReference);
        int orientation = PositionConstants.NONE;
        if (bounds.contains(preciseOrthoReference)) {
            int side = getClosestSide(ownReference, bounds);
            switch (side) {
                case PositionConstants.LEFT:
                case PositionConstants.RIGHT:
                    ownReference.setPreciseY(preciseOrthoReference.preciseY());
                    orientation = PositionConstants.HORIZONTAL;
                    break;
                case PositionConstants.TOP:
                case PositionConstants.BOTTOM:
                    ownReference.setPreciseX(preciseOrthoReference.preciseX());
                    orientation = PositionConstants.VERTICAL;
                    break;
                default:
                    throw new IllegalArgumentException(String.format("Position %s not supported", side));
            }
        } else if (preciseOrthoReference.preciseX() >= bounds.preciseX()
                && preciseOrthoReference.preciseX() <= bounds.preciseX() + bounds.preciseWidth()) {
            ownReference.setPreciseX(preciseOrthoReference.preciseX());
            orientation = PositionConstants.VERTICAL;
        } else if (preciseOrthoReference.preciseY() >= bounds.preciseY()
                && preciseOrthoReference.preciseY() <= bounds.preciseY() + bounds.preciseHeight()) {
            ownReference.setPreciseY(preciseOrthoReference.preciseY());
            orientation = PositionConstants.HORIZONTAL;
        }
        ownReference.updateInts();

        Point location = getLocation(ownReference, preciseOrthoReference);
        if (location == null) {
            location = getLocation(orthoReference);
            orientation = PositionConstants.NONE;
        }

        if (orientation != PositionConstants.NONE) {
            PrecisionPoint loc = new PrecisionPoint(location);
            if (orientation == PositionConstants.VERTICAL) {
                loc.setPreciseX(preciseOrthoReference.preciseX());
            } else {
                loc.setPreciseY(preciseOrthoReference.preciseY());
            }
            loc.updateInts();
            location = loc;
        }

        return location;
    }

    private static int getClosestSide(Point p, Rectangle r) {
        double diff = Math.abs(r.preciseX() + r.preciseWidth() - p.preciseX());
        int side = PositionConstants.RIGHT;
        double currentDiff = Math.abs(r.preciseX() - p.preciseX());
        if (currentDiff < diff) {
            diff = currentDiff;
            side = PositionConstants.LEFT;
        }
        currentDiff = Math.abs(r.preciseY() + r.preciseHeight() - p.preciseY());
        if (currentDiff < diff) {
            diff = currentDiff;
            side = PositionConstants.BOTTOM;
        }
        currentDiff = Math.abs(r.preciseY() - p.preciseY());
        if (currentDiff < diff) {
            side = PositionConstants.TOP;
        }
        return side;
    }

}
