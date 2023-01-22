package service.impl;

import data.Home;
import data.House;
import data.Resort;
import data.Villa;
import service.IHouseService;
import utility.ITypeUtility;
import utility.TypeUtility;

import java.math.BigDecimal;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

/**
 * house service
 */

public class HouseService implements IHouseService {
    protected ITypeUtility typeUtility;
    private int countOfHouseTypes = 3;

    public HouseService() {
        this.typeUtility = new TypeUtility();
    }

    /**
     * get total price of all type homes
     *
     * @return
     */
    public BigDecimal getTotalPriceOfHomes() {
        List<Home> homeList = this.typeUtility.getHomeList();
        BigDecimal sum = BigDecimal.valueOf(0);
        for (Home home : homeList) {
            BigDecimal price = home.getPrice();
            sum = sum.add(price);
        }
        return sum;
    }

    /**
     * get total price of all type villas
     *
     * @return
     */
    public BigDecimal getTotalPriceOfVillas() {
        List<Villa> villaList = this.typeUtility.getVillaList();
        BigDecimal sum = BigDecimal.valueOf(0);
        for (Home villa : villaList) {
            BigDecimal price = villa.getPrice();
            sum = sum.add(price);
        }
        return sum;
    }

    /**
     * get total price of all type resorts
     *
     * @return
     */
    public BigDecimal getTotalPriceOfResorts() {
        List<Resort> resortList = this.typeUtility.getResortList();
        BigDecimal sum = BigDecimal.valueOf(0);
        for (Resort resort : resortList) {
            BigDecimal price = resort.getPrice();
            sum = sum.add(price);
        }
        return sum;
    }

    /**
     * get total price of all type houses
     *
     * @return
     */
    public BigDecimal getTotalPriceOfAllTypeHouses() {
        BigDecimal sum = BigDecimal.valueOf(0).add(getTotalPriceOfResorts())
                .add(getTotalPriceOfVillas())
                .add(getTotalPriceOfHomes());
        return sum;
    }

    /**
     * get average price of all type houses
     *
     * @return
     */
    public OptionalDouble getAverageSquareMetersOfHomes() {
        List<Home> homeList = this.typeUtility.getHomeList();
        OptionalDouble average = OptionalDouble.of(homeList.stream().filter(o -> o.getSquareMeters() > 0)
                .mapToDouble(o -> o.getSquareMeters()).average().orElse(0));
        return average;
    }

    /**
     * get average price of all type houses
     *
     * @return
     */
    public OptionalDouble getAverageSquareMetersOfVillas() {
        List<Villa> villaList = this.typeUtility.getVillaList();
        OptionalDouble average = OptionalDouble.of(villaList.stream().filter(o -> o.getSquareMeters() > 0)
                .mapToDouble(o -> o.getSquareMeters()).average().orElse(0));
        return average;
    }

    /**
     * get average area of resorts
     *
     * @return
     */
    public OptionalDouble getAverageSquareMetersOfResorts() {
        List<Resort> resortList = this.typeUtility.getResortList();
        OptionalDouble average = OptionalDouble.of(resortList.stream().filter(o -> o.getSquareMeters() > 0)
                .mapToDouble(o -> o.getSquareMeters()).average().orElse(0));
        return average;
    }

    /**
     * get average area of houses
     *
     * @return
     */
    public OptionalDouble getAverageSquareMetersOfAllTypeHouses() {
        OptionalDouble average = OptionalDouble.of((getAverageSquareMetersOfHomes().getAsDouble() +
                getAverageSquareMetersOfVillas().getAsDouble() +
                getAverageSquareMetersOfResorts().getAsDouble()) / countOfHouseTypes);

        return average;
    }

    /**
     * get homes by filtering
     *
     * @param roomNumber
     * @param saloonNumber
     * @return
     */
    public List<House> getHomesByFilter(int roomNumber, int saloonNumber) {
        List<House> houses = this.typeUtility.getHouseList();

        List<House> result = houses.stream().filter(o -> o.getRoomNumber() == roomNumber && o.getSaloonNumber() == saloonNumber)
                .collect(Collectors.toList());
        if (result.isEmpty()) {
            throw new IllegalArgumentException("There is no house with this room number and saloon number");
        }
        ;
        return result;
    }
}
