package com.cooperativismo.sispautas.api.docs;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.cooperativismo.sispautas.domain.dto.PautaDTO;
import com.cooperativismo.sispautas.domain.entity.Pauta;
import com.cooperativismo.sispautas.exception.dto.ResponseError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "pauta", description = "Responsável por manter Pautas da cooperativa")
public interface PautaDocs {
	
	@Operation(summary = "Inclui nova pauta", tags = "pauta")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "201",
		            description = "Pauta incluída com Sucesso",
		            content = @Content(schema = @Schema(implementation = Pauta.class))
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
	ResponseEntity<Pauta> postPauta(@RequestBody PautaDTO pautaDto);
}
