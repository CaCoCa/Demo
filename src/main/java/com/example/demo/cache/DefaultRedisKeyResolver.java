/*
 * Copyright (c) 2019-2029, Dreamlu 卢春梦 (596392912@qq.com & www.dreamlu.net).
 * <p>
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE 3.0;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.demo.cache;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * 默认的 key 处理，添加配置的 key 前缀
 *
 * @author L.cm
 */
@RequiredArgsConstructor
public class DefaultRedisKeyResolver implements RedisKeyResolver {
	private final MicaRedisProperties properties;

	@Override
	public String resolve(String key) {
		String keyPrefix = properties.getKeyPrefix();
		return StringUtils.isBlank(keyPrefix) ? key : keyPrefix + CharPool.COLON + key;
	}

}
