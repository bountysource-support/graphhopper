/*
 *  Licensed to GraphHopper and Peter Karich under one or more contributor
 *  license agreements. See the NOTICE file distributed with this work for 
 *  additional information regarding copyright ownership.
 * 
 *  GraphHopper licenses this file to you under the Apache License, 
 *  Version 2.0 (the "License"); you may not use this file except in 
 *  compliance with the License. You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.graphhopper.util;

import com.graphhopper.util.shapes.BBox;
import com.graphhopper.util.shapes.GHPoint;

/**
 * Calculates the distance of two points or one point and an edge on earth via haversine formula.
 * Allows subclasses to implement less or more precise calculations.
 * <p/>
 * See http://en.wikipedia.org/wiki/Haversine_formula
 * <p/>
 * @author Peter Karich
 */
public interface DistanceCalc
{
    BBox createBBox( double lat, double lon, double radiusInMeter );

    double calcCircumference( double lat );

    /**
     * Calculates distance of (from, to) in meter.
     */
    double calcDist( double fromLat, double fromLon, double toLat, double toLon );

    /**
     * Returns the specified length in normalized meter.
     */
    double calcNormalizedDist( double dist );

    /**
     * Inverse to calcNormalizedDist. Returned the length in meter.
     */
    double calcDenormalizedDist( double normedDist );

    /**
     * Calculates in normalized meter
     */
    double calcNormalizedDist( double fromLat, double fromLon, double toLat, double toLon );

    /**
     * This method decides case 1: if we should use distance(r to edge) where r=(lat,lon) or case 2:
     * min(distance(r to a), distance(r to b)) where edge=(a to b)
     * <p/>
     * @return true for case 1
     */
    // case 1:
    //   r
    //  . 
    // a-------b
    //    
    // case 2:
    // r
    //  .
    //    a-------b
    boolean validEdgeDistance( double r_lat_deg, double r_lon_deg,
            double a_lat_deg, double a_lon_deg,
            double b_lat_deg, double b_lon_deg );

    /**
     * This method calculates the distance from r to edge (a, b) where the crossing point is c
     * <p/>
     * @return the distance in normalized meter
     */
    double calcNormalizedEdgeDistance( double r_lat_deg, double r_lon_deg,
            double a_lat_deg, double a_lon_deg,
            double b_lat_deg, double b_lon_deg );

    /**
     * @return the crossing point c of the vertical line from r to line (a, b)
     */
    GHPoint calcCrossingPointToEdge( double r_lat_deg, double r_lon_deg,
            double a_lat_deg, double a_lon_deg,
            double b_lat_deg, double b_lon_deg );
}
