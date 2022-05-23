package com.cooperativismo.sispautas.api.docs;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.cooperativismo.sispautas.domain.dto.VotoDTO;
import com.cooperativismo.sispautas.domain.entity.Voto;
import com.cooperativismo.sispautas.exception.dto.ResponseError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "voto", description = "Responsável por manter voto das pautas")
public interface VotoDocs {
	
	
	@Operation(summary = "Faz a votação do associado", tags = "voto")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "200",
		            description = "Pauta votada com sucesso",
		            content = @Content(schema = @Schema(implementation = Voto.class))
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
	ResponseEntity<Voto> postVotarPauta(@RequestBody VotoDTO votoDTO);
}
