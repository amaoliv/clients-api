package ibm.github.amandaoliveira.clients.mapper;

import ibm.github.amandaoliveira.clients.models.ClientEntity;
import ibm.github.amandaoliveira.clients.models.ClientInput;
import ibm.github.amandaoliveira.clients.models.ClientOutput;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    //input to entity conversion
    ClientEntity toClientEntity (ClientInput clientInput);

    //entity to output conversion
    ClientOutput toClientOutput(ClientEntity entity);

}
