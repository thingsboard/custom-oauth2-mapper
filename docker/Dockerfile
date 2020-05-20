#
# Copyright © 2016-2018 The Thingsboard Authors
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

FROM thingsboard/openjdk8

COPY start-oauth2-mapper.sh ${pkg.name}.conf ${pkg.name}.deb /tmp/

RUN chmod a+x /tmp/*.sh \
    && mv /tmp/start-oauth2-mapper.sh /usr/bin

RUN dpkg -i /tmp/${pkg.name}.deb

RUN update-rc.d ${pkg.name} disable

RUN mv /tmp/${pkg.name}.conf ${pkg.installFolder}/conf

ENV OAUTH2_MAPPER_DOCKER_NAME=oauth2-mapper

ENV SERVER_PORT=10010
ENV CUSTOM_MAPPER_USERNAME=admin
ENV CUSTOM_MAPPER_PASSWORD="{noop}password"

RUN chown -R ${pkg.user}:${pkg.user} /tmp

RUN chmod 555 ${pkg.installFolder}/bin/${pkg.name}.jar

USER ${pkg.user}

CMD ["start-oauth2-mapper.sh"]