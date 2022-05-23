package com.cooperativismo.sispautas.api.docs;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.cooperativismo.sispautas.domain.entity.Associado;
import com.cooperativismo.sispautas.exception.dto.ResponseError;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "associado", description = "Responsável por manter Associados da cooperativa")
public interface AssociadoDocs {
	
	@Operation(summary = "Inclui novo associado", tags = "associado")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "201",
		            description = "Associado incluído com Sucesso",
		            content = @Content(schema = @Schema(implementation = Associado.class))
		    ),
		    @ApiResponse(responseCode = "400",
		            content = @Content(
		                    mediaType = MediaType.APPLICATION_JSON_VALUE,
		                    schema = @Schema(implementation = ResponseError.class))
		    ),
		    @ApiResponse(responseCode = "404",
		            content = @Content(
		                    mediaType = MediaType.APPLICATION_JSON_VALUE,
		                    schema = @Schema(implementation = ResponseError.class))
		    ),
		    @ApiResponse(responseCode = "422",
            		content = @Content(
            				mediaType = MediaType.APPLICATION_JSON_VALUE,
            				schema = @Schema(implementation = ResponseError.class))
    ),
		    @ApiResponse(responseCode = "500",
		            content = @Content(
		                    mediaType = MediaType.APPLICATION_JSON_VALUE,
		                    schema = @Schema(implementation = ResponseError.class))
		    ),

	    })
	ResponseEntity<Associado> postAssociado(@RequestBody Associado associado);
}
