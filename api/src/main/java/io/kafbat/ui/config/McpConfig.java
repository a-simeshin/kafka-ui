package io.kafbat.ui.config;

import io.kafbat.ui.service.mcp.McpSpecificationGenerator;
import io.kafbat.ui.service.mcp.McpTool;
import io.modelcontextprotocol.server.McpServerFeatures.AsyncToolSpecification;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(value = "spring.ai.mcp.server.enabled", havingValue = "true")
public class McpConfig {

  private final List<McpTool> mcpTools;
  private final McpSpecificationGenerator mcpSpecificationGenerator;

  @Bean
  public List<AsyncToolSpecification> kafkaUiMcpTools() {
    return mcpTools.stream()
        .flatMap(tool -> mcpSpecificationGenerator.convertTool(tool).stream())
        .toList();
  }
}
