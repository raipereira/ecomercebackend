package raiper.miu.cs489.dto.converter.dtoToEntity;

import org.springframework.core.convert.converter.Converter;
import raiper.miu.cs489.dto.request.OrderRequest;
import raiper.miu.cs489.model.Order;

public class ConverterRequestToOrder implements Converter<OrderRequest, Order> {


    @Override
    public Order convert(OrderRequest source) {

        return null;
    }
}
