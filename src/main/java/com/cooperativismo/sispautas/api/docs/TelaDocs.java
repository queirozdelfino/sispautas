package com.cooperativismo.sispautas.api.docs;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import com.cooperativismo.sispautas.domain.dto.tela.TelaFormularioDTO;
import com.cooperativismo.sispautas.domain.dto.tela.TelaSelecaoDTO;
import com.cooperativismo.sispautas.exception.dto.ResponseError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "tela", description = "Responsável por gerar as telas para o cliente.")
public interface TelaDocs {
	
	@Operation(summary = "Retorna tela de novo associado", tags = "tela")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "200",
		            description = "Tela buscada com sucesso",
		            content = @Content(schema = @Schema(implementation = TelaFormularioDTO.class))
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
	ResponseEntity<TelaFormularioDTO> getTelaIncluirAssociado();
	
	@Operation(summary = "Retorna tela de incluir nova pauta", tags = "tela")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "200",
		            description = "Tela buscada com sucesso",
		            content = @Content(schema = @Schema(implementation = TelaFormularioDTO.class))
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
	ResponseEntity<TelaFormularioDTO> getTelaIncluirPauta();
	
	@Operation(summary = "Retorna tela de selecionar pautas para abrir sessão de votação", tags = "tela")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "200",
		            description = "Tela buscada com sucesso",
		            content = @Content(schema = @Schema(implementation = TelaSelecaoDTO.class))
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
	ResponseEntity<TelaSelecaoDTO> getTelaSelecionarPautaParaVotar();
	
	@Operation(summary = "Retorna tela de formulário para abrir uma sessão de votos", tags = "tela")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "200",
		            description = "Tela buscada com sucesso",
		            content = @Content(schema = @Schema(implementation = TelaFormularioDTO.class))
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
	ResponseEntity<TelaFormularioDTO> getTelaAbrirSessao(Long pautaId);
	
	@Operation(summary = "Retorna tela de formulário para votar uma pauta aberta", tags = "tela")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "200",
		            description = "Tela buscada com sucesso",
		            content = @Content(schema = @Schema(implementation = TelaFormularioDTO.class))
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
	ResponseEntity<TelaFormularioDTO> getTelaVotarPauta(Long pautaId);
	
	@Operation(summary = "Retorna tela de formulário para mostrar resultado de uma pauta fechada", tags = "tela")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "200",
		            description = "Tela buscada com sucesso",
		            content = @Content(schema = @Schema(implementation = TelaFormularioDTO.class))
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
	ResponseEntity<TelaFormularioDTO> getTelaApresentarResultadoPauta(Long pautaId);

}
