package com.mxt.anitrend.util;

import android.text.TextUtils;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.mxt.anitrend.model.entity.anilist.Series;
import com.mxt.anitrend.model.entity.base.CharacterBase;
import com.mxt.anitrend.model.entity.base.SeriesBase;
import com.mxt.anitrend.model.entity.general.SeriesList;
import com.mxt.anitrend.model.entity.group.EntityGroup;
import com.mxt.anitrend.model.entity.group.EntityHeader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by max on 2018/02/18.
 * Utils for group various types of media
 */

public class GroupingUtil {

    /**
     * Groups series relations by the relation type of the series
     */
    public static List<EntityGroup> getGroupedSeriesRelated(Series series) {
        List<Series> seriesList = new ArrayList<>();
        if(series != null) {
            if(series.getRelations() != null)
                seriesList.addAll(series.getRelations());
            if(series.getRelations_anime() != null)
                seriesList.addAll(series.getRelations_anime());
            if(series.getRelations_manga() != null)
                seriesList.addAll(series.getRelations_manga());
        }
        List<EntityGroup> entityMap = new ArrayList<>();
        if(!seriesList.isEmpty()) {
            Map<String, List<Series>> map = Stream.of(seriesList)
                    .filter(value -> !TextUtils.isEmpty(value.getRelation_type()))
                    .collect(Collectors.groupingBy(Series::getRelation_type));
            for (Map.Entry<String, List<Series>> entry: CompatUtil.getKeyFilteredMap(map)) {
                entityMap.add(new EntityHeader(entry.getKey(), entry.getValue().size()));
                entityMap.addAll(entry.getValue());
            }
        }
        return entityMap;
    }

    /**
     * Groups series relations by the relation type of the series
     */
    public static List<EntityGroup> getGroupedSeriesBaseType(List<SeriesBase> seriesList) {
        List<EntityGroup> entityMap = new ArrayList<>();
        if(seriesList != null && !seriesList.isEmpty()) {
            Map<String, List<SeriesBase>> map = Stream.of(seriesList)
                    .filter(value -> !TextUtils.isEmpty(value.getType()))
                    .collect(Collectors.groupingBy(SeriesBase::getType));
            for (Map.Entry<String, List<SeriesBase>> entry: CompatUtil.getKeyFilteredMap(map)) {
                entityMap.add(new EntityHeader(entry.getKey(), entry.getValue().size()));
                entityMap.addAll(entry.getValue());
            }
        }
        return entityMap;
    }

    /**
     * Groups series relations by the relation type of the series
     */
    public static List<EntityGroup> getGroupedSeriesType(List<Series> seriesList) {
        List<EntityGroup> entityMap = new ArrayList<>();
        if(seriesList != null && !seriesList.isEmpty()) {
            Map<String, List<Series>> map = Stream.of(seriesList)
                    .filter(value -> !TextUtils.isEmpty(value.getType()))
                    .collect(Collectors.groupingBy(Series::getType));
            for (Map.Entry<String, List<Series>> entry: CompatUtil.getKeyFilteredMap(map)) {
                entityMap.add(new EntityHeader(entry.getKey(), entry.getValue().size()));
                entityMap.addAll(entry.getValue());
            }
        }
        return entityMap;
    }

    /**
     * Groups series relations by the relation type of the role type
     */
    public static List<EntityGroup> getGroupedSeriesRoleType(List<SeriesBase> seriesList) {
        List<EntityGroup> entityMap = new ArrayList<>();
        if(seriesList != null && !seriesList.isEmpty()) {
            Map<String, List<SeriesBase>> map = Stream.of(seriesList)
                    .filter(value -> !TextUtils.isEmpty(value.getType()))
                    .collect(Collectors.groupingBy(SeriesBase::getRole));
            for (Map.Entry<String, List<SeriesBase>> entry: CompatUtil.getKeyFilteredMap(map)) {
                entityMap.add(new EntityHeader(entry.getKey(), entry.getValue().size()));
                entityMap.addAll(entry.getValue());
            }
        }
        return entityMap;
    }

    /**
     * Groups series relations by the type
     */
    public static List<EntityGroup> getGroupedSeriesListType(List<SeriesList> seriesList) {
        List<EntityGroup> entityMap = new ArrayList<>();
        if(seriesList != null && !seriesList.isEmpty()) {
            Map<String, List<SeriesList>> map = Stream.of(seriesList)
                    .filter(value -> !TextUtils.isEmpty(SeriesUtil.getSeriesModel(value).getType()))
                    .collect(Collectors.groupingBy(o -> SeriesUtil.getSeriesModel(o).getType()));

            for (Map.Entry<String, List<SeriesList>> entry: CompatUtil.getKeyFilteredMap(map)) {
                entityMap.add(new EntityHeader(entry.getKey(), entry.getValue().size()));
                entityMap.addAll(entry.getValue());
            }
        }
        return entityMap;
    }

    /**
     * Groups characters based on roles
     */
    public static List<EntityGroup> getGroupedRoleCharacters(Series series) {
        List<EntityGroup> entityMap = new ArrayList<>();
        if(!series.getCharacters().isEmpty()) {
            Map<String, List<CharacterBase>> map = Stream.of(series.getCharacters())
                    .filter(value -> !TextUtils.isEmpty(value.getRole()))
                    .collect(Collectors.groupingBy(CharacterBase::getRole));
            for (Map.Entry<String, List<CharacterBase>> entry: CompatUtil.getKeyFilteredMap(map)) {
                entityMap.add(new EntityHeader(entry.getKey(), entry.getValue().size()));
                entityMap.addAll(entry.getValue());
            }
        }
        return entityMap;
    }
}