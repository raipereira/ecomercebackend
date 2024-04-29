package raiper.miu.cs489.dto.converter.entityToDto;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import raiper.miu.cs489.dto.response.OrderResponse;
import raiper.miu.cs489.dto.response.ProductResponse;
import raiper.miu.cs489.model.Order;
import raiper.miu.cs489.model.Product;


@Component
public class ConverterOrderToDto implements Converter<Order, OrderResponse> {

    private ConverterCustomerToDto converterCustomerToDto;
    public ConverterOrderToDto(ConverterCustomerToDto converterCustomerToDto) {
        this.converterCustomerToDto = converterCustomerToDto;
    }

    @Override
    public OrderResponse convert(Order source) {
       var orderResponse = new OrderResponse(
               source.getOrderId(),
               source.getOrderDate(),
               converterCustomerToDto.convert(source.getCustomer())
       );
        return orderResponse;
    }
}
