/**************************************************************************
 *  Copyright (C) 2010 Atlas of Living Australia
 *  All Rights Reserved.
 * 
 *  The contents of this file are subject to the Mozilla Public
 *  License Version 1.1 (the "License"); you may not use this file
 *  except in compliance with the License. You may obtain a copy of
 *  the License at http://www.mozilla.org/MPL/
 * 
 *  Software distributed under the License is distributed on an "AS
 *  IS" basis, WITHOUT WARRANTY OF ANY KIND, either express or
 *  implied. See the License for the specific language governing
 *  rights and limitations under the License.
 ***************************************************************************/
package org.ala.dao;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.ala.dto.SearchResultsDTO;
import org.ala.dto.SearchTaxonConceptDTO;
import org.ala.util.StatusType;

/**
 * Interface for searching taxon concept profiles.
 *
 * @author "Nick dos Remedios <Nick.dosRemedios@csiro.au>"
 */
public interface FulltextSearchDao {

    /**
     * Search for taxon concept with the following status type
     *
     * @param statusType
     * @param filterQuery
     * @param startIndex
     * @param pageSize
     * @param sortField
     * @param sortDirection
     * @return
     * @throws Exception
     */
    SearchResultsDTO findAllByStatus(StatusType statusType, String filterQuery, Integer startIndex,
            Integer pageSize, String sortField, String sortDirection) throws Exception;

    SearchResultsDTO findAllSpeciesByRegionAndHigherTaxon(String regionType, 
    		String regionName, String rank, String higherTaxon,
    		String filterQuery, Integer startIndex, Integer pageSize,
            String sortField, String sortDirection) throws Exception;
    
    SearchResultsDTO findAllSpeciesByRegionAndHigherTaxon(String regionType, String regionName, 
    		String rank, List<String> higherTaxa, 
    		String filterQuery, Integer startIndex, Integer pageSize,
            String sortField, String sortDirection) throws Exception;
    
    
    /**
     * Retrieves a list of species that have been recorded in one region
     * but not in another.
     * 
     * @param regionType the region type to test
     * @param regionName the region name
     * @param altRegionType the region type to compare to
     * @param altRegionName the region name to compare to
     * @param rank
     * @param higherTaxa
     * @param filterQuery
     * @param startIndex
     * @param pageSize
     * @param sortField
     * @param sortDirection
     * @return
     * @throws Exception
     */
    SearchResultsDTO findAllDifferencesInSpeciesByRegionAndHigherTaxon(
    		String regionType, String regionName, 
    		String altRegionType, String altRegionName, 
    		String rank, List<String> higherTaxa, 
    		String filterQuery, Integer startIndex, Integer pageSize,
            String sortField, String sortDirection) throws Exception;
    
    int countSpeciesByRegionAndHigherTaxon(String regionType, String regionName, 
    		 String rank, String higherTaxon) throws Exception;
    
    int countSpeciesByRegionAndHigherTaxon(String regionType,
			String regionName, String rank, List<String> higherTaxa)
			throws Exception;

    int writeSpeciesByRegionAndHigherTaxon(String regionType, String regionName, 
    		String rank, String higherTaxon, OutputStream output)
			throws Exception;
    
    int writeSpeciesByRegionAndHigherTaxon(String regionType, String regionName, 
    		String rank, List<String> higherTaxa, OutputStream output)
			throws Exception;
    
    /**
     * Search for taxon concept with the following scientific name.
     *
     * @param input
     * @param limit
     * @return
     * @throws Exception
     */
    List<SearchTaxonConceptDTO> findByScientificName(String input, int limit) throws Exception;

    /**
     * Search for taxon concept with the following scientific name with parameters for paging & sorting
     *
     * @param query
     * @param filterQuery
     * @param startIndex
     * @param pageSize
     * @param sortField
     * @param sortDirection
     * @return
     * @throws Exception
     */
    SearchResultsDTO findByScientificName(String query, String filterQuery, Integer startIndex,
            Integer pageSize, String sortField, String sortDirection) throws Exception;

    /**
     * For every dataset (infoSource) get a count of the number of taxon concepts which contain indexed
     * information from that dataset.
     *
     * @return
     */
    Map<String, Long> getAllDatasetCounts();
}